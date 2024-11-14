package com.example.Test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.Test.model.DAOLoginRequest;
import com.example.Test.model.DAORegister;
import com.example.Test.service.AuthService;

@RestController
public class TestController {

    private final AuthService authService;

    public TestController(AuthService authService) {
        this.authService = authService;
    }
    
    @GetMapping("/home")
    public String home() {
        return "Hello, world";
    }
    @PostMapping("/auth/register")
    public String register(@RequestBody DAORegister request) {
        return authService.register(request);
   }
    @PostMapping("/auth/login")
    public String login(@RequestBody DAOLoginRequest request) {
        return authService.login(request);
    }

}
