package com.example.usuariosApi.Usuario.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/")
    public String teste(Authentication authentication) {

        return "ola " + authentication.getName().toString();
    }
}
