package com.example.Test.model;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {
    
    @Id
    @Column(columnDefinition = "CHAR(36)", nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();

    // we use the email as username;
    @Nonnull
    @Column(name = "email")
    private String username;

    @Nonnull
    @JsonIgnore
    private String password;
    @Nonnull
    private String role = "USER";
    @Nonnull
    private String name;

    // Default cons for Spring
    public User() {

    }

    public User(String email, String password, String name) {
        this.username = email;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    // Email is used as username 
    public void setEmail(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    
}
