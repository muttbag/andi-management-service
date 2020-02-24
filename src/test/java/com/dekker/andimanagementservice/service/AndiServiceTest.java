package com.dekker.andimanagementservice.service;

import com.dekker.andimanagementservice.Andi;
import com.dekker.andimanagementservice.CreateAndiRequest;
import com.dekker.andimanagementservice.CreateAndiResponse;
import com.dekker.andimanagementservice.GetAndiRequest;
import com.dekker.andimanagementservice.common.CamelCaseDisplayNameGenerator;
import com.dekker.andimanagementservice.dao.AndiDao;
import com.dekker.andimanagementservice.exception.AndiNotFoundException;
import com.dekker.andimanagementservice.repository.AndiRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CamelCaseDisplayNameGenerator.class)
public class AndiServiceTest {

    @Mock
    private AndiRepository andiRepository;

    @Mock
    private StreamObserver streamObserver;

    @InjectMocks
    final AndiService testClass = new AndiService();

    private static AndiDao ANDI = new AndiDao("Martin", "Hutton", "PD");

    @Test
    public void successfulCreateAndi() {
        final AndiDao daoObject = new AndiDao("Martin", "Hutton", "PD");
        final CreateAndiRequest request = CreateAndiRequest.newBuilder().setFirstName("Martin").setSecondName("Hutton").setRole("PD").build();

        testClass.createAndi(request, streamObserver);

        Mockito.verify(andiRepository).save(daoObject);
        Mockito.verify(streamObserver).onNext(CreateAndiResponse.newBuilder().setSuccess(true).build());
        Mockito.verify(streamObserver).onCompleted();
    }

    @Test
    public void successfulRetrieveAndi(){
        final GetAndiRequest request = GetAndiRequest.newBuilder().setId(1).build();
        final Andi andi = Andi.newBuilder().setFirstName("Martin").setSecondName("Hutton").setRole("PD").build();

        Mockito.when(andiRepository.findById(request.getId())).thenReturn(Optional.of(ANDI));

        testClass.getAndi(request, streamObserver);

        Mockito.verify(streamObserver).onNext(andi);
        Mockito.verify(streamObserver).onCompleted();
    }

    @Test
    public void andiNotFound(){
        final var request = GetAndiRequest.newBuilder().setId(1).build();

        Mockito.when(andiRepository.findById(request.getId())).thenReturn(Optional.empty());

        final var exceptionThrown = Assertions.assertThrows(AndiNotFoundException.class, () -> testClass.getAndi(request, streamObserver), "Unable to find Andi with id: " + request.getId());
        Assertions.assertEquals(Status.NOT_FOUND, exceptionThrown.getStatus());
    }
}
