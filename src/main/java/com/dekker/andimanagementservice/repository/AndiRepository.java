package com.dekker.andimanagementservice.repository;

import com.dekker.andimanagementservice.dao.AndiDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AndiRepository extends CrudRepository<AndiDao, Integer> {

    Optional<AndiDao> findById(Integer Id);
}