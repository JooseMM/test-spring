package com.example.Test.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Test.model.DAOLoginRequest;
import com.example.Test.model.DAORegister;
import com.example.Test.model.User;
import com.example.Test.repository.UserRepository;


@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsService;

    public AuthService(UserRepository userRepository,
                      AuthenticationManager authenticationManager,
                      PasswordEncoder passwordEncoder,
                      JwtService jwtService,
                      UserDetailsServiceImp userDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public String login(@RequestBody DAOLoginRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(request.email(),
                request.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if(authenticationResponse.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationResponse.getName()); //getName will get us the email which is use as the main identifier for the user in this case
            return jwtService.getToken(userDetails);
        }
        return "User is not found";

    }

    public String register(@RequestBody DAORegister request) {
        User user = new User(request.email(), passwordEncoder.encode(request.password()), request.name());
        userRepository.save(user);
        return "User: " + request.name() + "Created!";
    }
}
