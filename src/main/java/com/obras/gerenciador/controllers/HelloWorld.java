package com.obras.gerenciador.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@SecurityRequirement(name = "bearer-key")
public class HelloWorld {

    @GetMapping
    public ResponseEntity olaMundo(){
        return ResponseEntity.ok("Hello World!");
    }
}
