package com.example.Test.models;

import com.example.Test.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Nonnull
    private String email;
    @Nonnull
    private String name;
    @Nonnull
    private ERole role = ERole.USER;
    @Nonnull
    @JsonIgnore
    private String password;

    public UserEntity() {
    }
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ERole getRole() {
        return role;
    }
    public String getPassword() {
        return this.password;
    }
    public void  setPassword(String newPassword) {
        this.password = newPassword;
    }
}
