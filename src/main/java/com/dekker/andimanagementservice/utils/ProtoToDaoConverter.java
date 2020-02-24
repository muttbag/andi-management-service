package com.dekker.andimanagementservice.utils;

import com.dekker.andimanagementservice.Andi;
import com.dekker.andimanagementservice.CreateAndiRequest;
import com.dekker.andimanagementservice.dao.AndiDao;
import com.dekker.andimanagementservice.service.AndiService;

import java.util.Objects;

public final class ProtoToDaoConverter {

    //Refactor into factory and rename class

    public static AndiDao convertAndiRequestProtoToAndiDao(final CreateAndiRequest requestObject) {

        Objects.requireNonNull(requestObject, "CreateAndiRequest cannot be null for conversion");
        return new AndiDao(requestObject.getFirstName(), requestObject.getSecondName(), requestObject.getRole());
    }

    public static Andi convertAndiDaoToAndi(final AndiDao andiDao) {

        Objects.requireNonNull(andiDao, "AndiDao cannot be null for conversion");

        return Andi.newBuilder().setFirstName(andiDao.getFirstName()).setSecondName(andiDao.getLastName()).setRole(andiDao.getRole()).build();
    }

}
