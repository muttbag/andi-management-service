package com.dekker.andimanagementservice.service;

import com.dekker.andimanagementservice.*;
import com.dekker.andimanagementservice.Andi;
import com.dekker.andimanagementservice.AndiServiceGrpc;
import com.dekker.andimanagementservice.CreateAndiRequest;
import com.dekker.andimanagementservice.CreateAndiResponse;
import com.dekker.andimanagementservice.GetAndiRequest;
import com.dekker.andimanagementservice.dao.AndiDao;
import com.dekker.andimanagementservice.exception.AndiNotFoundException;
import com.dekker.andimanagementservice.repository.AndiRepository;
import com.dekker.andimanagementservice.utils.ProtoToDaoConverter;
import com.google.rpc.StatusProto;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GRpcService
public class AndiService extends AndiServiceGrpc.AndiServiceImplBase {

    @Autowired
    private AndiRepository repository;

    @Override
    public void getAndi(GetAndiRequest request, StreamObserver<Andi> responseObserver) {

        final AndiDao andiDao = repository.findById(request.getId()).orElseThrow( () -> {
            throw new AndiNotFoundException(Status.NOT_FOUND, "Unable to find Andi with id: " + request.getId());
        });

        responseObserver.onNext(ProtoToDaoConverter.convertAndiDaoToAndi(andiDao));
        responseObserver.onCompleted();
    }

    @Override
    public void createAndi(CreateAndiRequest request, StreamObserver<CreateAndiResponse> responseObserver) {

        final AndiDao convertedDao = ProtoToDaoConverter.convertAndiRequestProtoToAndiDao(request);

        repository.save(convertedDao);

        responseObserver.onNext(CreateAndiResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }
}