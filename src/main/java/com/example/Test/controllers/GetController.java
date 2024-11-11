package com.example.Test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    
    @GetMapping("/home")
    public String getHome() {
        return "Hello, this is my home!";
    }
}
