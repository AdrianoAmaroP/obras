package com.obras.gerenciador.controllers;

import com.obras.gerenciador.domain.login.entities.RequestCadastroUsuario;
import com.obras.gerenciador.domain.login.entities.RequestUsuario;
import com.obras.gerenciador.domain.login.entities.ResponseToken;
import com.obras.gerenciador.domain.login.entities.Usuario;
import com.obras.gerenciador.domain.login.respository.UsuarioRepository;
import com.obras.gerenciador.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid RequestUsuario req){

        var token = new UsernamePasswordAuthenticationToken(req.nome(), req.senha());
        var authentication = manager.authenticate(token);
        var dto = new ResponseToken(tokenService.gerarToken((Usuario) authentication.getPrincipal()));

        return ResponseEntity.ok(dto);
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