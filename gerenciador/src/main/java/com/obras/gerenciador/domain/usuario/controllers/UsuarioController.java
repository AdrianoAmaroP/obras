package com.obras.gerenciador.domain.usuario.controllers;

import com.obras.gerenciador.domain.usuario.entities.RequestUsuario;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @PostMapping
    public ResponseEntity logar(@RequestBody RequestUsuario req){
        return ResponseEntity.ok("ola "+ req.nome() +", seu token: 123");
    }

    @PostMapping("/cadastra")
    public ResponseEntity cadastrar(@RequestBody RequestUsuario req){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
