package com.example.Test.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Test.interfaces.UserEntityRepository;
import com.example.Test.models.DaoLoginRequest;
import com.example.Test.models.DaoRegisterRequest;
import com.example.Test.models.UserEntity;

@RestController()
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager,
     PasswordEncoder passwordEncoder,
     UserEntityRepository userRepository
     ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody DaoLoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        logger.info("pass filter? " + authenticationResponse.isAuthenticated());
        if(authenticationResponse.isAuthenticated()) {
            return new ResponseEntity<>("Is authenticatedd", HttpStatusCode.valueOf(200));
        }
            return new ResponseEntity<>("Is not authenticatedd", HttpStatusCode.valueOf(403));
        }
    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody DaoRegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(registerRequest.email());
        user.setName(registerRequest.name());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatusCode.valueOf(201));
    }
}

