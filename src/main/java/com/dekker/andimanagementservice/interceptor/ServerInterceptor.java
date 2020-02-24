package com.dekker.andimanagementservice.interceptor;

import com.dekker.andimanagementservice.exception.AndiException;
import io.grpc.*;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.core.annotation.Order;

@GRpcGlobalInterceptor
@Order(1)
public class ServerInterceptor implements io.grpc.ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        ServerCall.Listener<ReqT> delegate = next.startCall(call, headers);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(delegate) {

            @Override
            public void onHalfClose() {
                try {
                    super.onHalfClose();
                } catch (AndiException e) {
                    call.close(e.getStatus()
                            .withCause(e)
                            .withDescription(e.getMessage()), new Metadata());
                } catch (Exception e) {
                    call.close(Status.INTERNAL
                            .withCause(e)
                            .withDescription(e.getMessage()), new Metadata());
                }
            }
        };
    }
}
