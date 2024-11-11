package com.example.Test.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.Test.models.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);
}   
