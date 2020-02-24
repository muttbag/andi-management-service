package com.dekker.andimanagementservice.integration;

import com.dekker.andimanagementservice.Andi;
import com.dekker.andimanagementservice.AndiServiceGrpc;
import com.dekker.andimanagementservice.CreateAndiRequest;
import com.dekker.andimanagementservice.CreateAndiResponse;
import com.dekker.andimanagementservice.GetAndiRequest;
import com.dekker.andimanagementservice.exception.AndiNotFoundException;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AndiServiceIT {

    private AndiServiceGrpc.AndiServiceBlockingStub stub;

    private ManagedChannel channel;

    @Autowired
    private GRpcServerProperties serverProperties;

    @BeforeEach
    public void setUp() {
        channel = InProcessChannelBuilder.forName(serverProperties.getInProcessServerName()).usePlaintext().build();
        stub = AndiServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    @Test
    public void tearDown() {
        channel.shutdownNow();
        Assertions.assertTrue(channel.isShutdown());
    }

    @Test
    public void shouldReturnSuccessWhenCreatingAndi() {

        final CreateAndiRequest request = CreateAndiRequest.newBuilder().setFirstName("Simon").setSecondName("Brown").setRole("Product Developer").build();

        final CreateAndiResponse response = stub.createAndi(request);

        Assertions.assertTrue(response.getSuccess());
    }

    @Test
    public void shouldReturnAndi() {

        final CreateAndiRequest request = CreateAndiRequest.newBuilder().setFirstName("James").setSecondName("Smith").setRole("Product Developer").build();
        stub.createAndi(request);

        final GetAndiRequest getRequest = GetAndiRequest.newBuilder().setId(5).build();

        final Andi response = stub.getAndi(getRequest);

        Assertions.assertEquals(request.getFirstName(), response.getFirstName());
        Assertions.assertEquals(request.getSecondName(), response.getSecondName());
        Assertions.assertEquals(request.getRole(), response.getRole());
    }

    @Test
    void shouldReturnNotFoundWhenAndiDoesNotExist() {

        final var notExistingAndiId = 10;
        final var getRequest = GetAndiRequest.newBuilder().setId(notExistingAndiId).build();
        final var actual = Assertions.assertThrows(StatusRuntimeException.class, () ->  stub.getAndi(getRequest));

        Assertions.assertEquals(Status.Code.NOT_FOUND, actual.getStatus().getCode());
        Assertions.assertEquals("Unable to find Andi with id: " + notExistingAndiId, actual.getStatus().getDescription());
    }
}