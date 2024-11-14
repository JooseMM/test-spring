package com.example.Test.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.Test.model.User;

public interface UserRepository extends CrudRepository<User, UUID>{
    Optional<User> getUserByUsername(String email);
}
