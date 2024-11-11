package com.example.Test.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.Test.interfaces.UserEntityRepository;
import com.example.Test.models.SecurityUser;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    private final UserEntityRepository userEntityRepository;

    public UserDetailsServiceImp(UserEntityRepository userEntityRepository){
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    private UserDetails loadUserByEmail(String email) {
        return userEntityRepository.findUserByEmail(email)
                .map(foundUser -> new SecurityUser(foundUser))
                .orElseThrow(() -> new UsernameNotFoundException("User not found by matching email: " + email));
    }

    
}
