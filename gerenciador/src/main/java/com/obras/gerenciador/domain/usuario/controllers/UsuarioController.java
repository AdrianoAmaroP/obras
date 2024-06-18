package com.obras.gerenciador.domain.usuario.controllers;

import com.obras.gerenciador.domain.usuario.entities.RequestCadastroUsuario;
import com.obras.gerenciador.domain.usuario.entities.RequestUsuario;
import com.obras.gerenciador.domain.usuario.entities.Usuario;
import com.obras.gerenciador.domain.usuario.respository.UsuarioRepository;
import jakarta.validation.Valid;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid RequestUsuario req){

        var token = new UsernamePasswordAuthenticationToken(req.nome(), req.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid RequestCadastroUsuario req){
        if(!req.chave().equals("123")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("chave incorreta");
        }
        var user = new Usuario(null, req.nome(), encoder.encode(req.senha()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}