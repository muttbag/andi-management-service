package com.dekker.andimanagementservice.client;

import com.dekker.andimanagementservice.AndiServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SearchServiceClient {

    public void test() {

        System.out.println("Creating client...");
        final ManagedChannel client = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();


        AndiServiceGrpc.AndiServiceBlockingStub stub = AndiServiceGrpc.newBlockingStub(client);

        com.dekker.andimanagementservice.GetAndiRequest request = com.dekker.andimanagementservice.GetAndiRequest.newBuilder().setId(1).build();

        System.out.println("Executing request...");

        final com.dekker.andimanagementservice.Andi response = stub.getAndi(request);

        System.out.println(response);
    }

}
