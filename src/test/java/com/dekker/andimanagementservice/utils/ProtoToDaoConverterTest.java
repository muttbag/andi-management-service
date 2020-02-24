package com.dekker.andimanagementservice.utils;


import com.dekker.andimanagementservice.Andi;
import com.dekker.andimanagementservice.CreateAndiRequest;
import com.dekker.andimanagementservice.common.CamelCaseDisplayNameGenerator;
import com.dekker.andimanagementservice.dao.AndiDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(CamelCaseDisplayNameGenerator.class)
public class ProtoToDaoConverterTest {

    final AndiDao ANDI_DAO =  new AndiDao("name", "second name", "role");

    @Test
    public void objectToConvertNullException() {

        final Exception thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            ProtoToDaoConverter.convertAndiRequestProtoToAndiDao(null);
        });


        Assertions.assertEquals(thrown.getMessage(), "CreateAndiRequest cannot be null for conversion");
    }

    @Test
    public void createAndiRequestProtoConvertedToDao() {

        final CreateAndiRequest request = CreateAndiRequest.newBuilder()
                                                            .setFirstName("name")
                                                            .setSecondName("second name")
                                                            .setRole("role")
                                                            .build();

        final AndiDao actual = ProtoToDaoConverter.convertAndiRequestProtoToAndiDao(request);

        Assertions.assertEquals(ANDI_DAO, actual);
    }

    @Test
    public void createAndiFromAndiDao(){

        final Andi expected = Andi.newBuilder().setFirstName("name").setSecondName("second name").setRole("role").build();
        final Andi actual = ProtoToDaoConverter.convertAndiDaoToAndi(ANDI_DAO);

        Assertions.assertEquals(expected, actual);
    }
}
