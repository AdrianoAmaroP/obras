package com.obras.gerenciador.controllers;

import com.obras.gerenciador.domain.login.entities.RequestCadastroUsuario;
import com.obras.gerenciador.domain.login.entities.RequestUsuario;
import com.obras.gerenciador.domain.login.entities.ResponseToken;
import com.obras.gerenciador.domain.login.entities.User;
import com.obras.gerenciador.domain.login.repository.UsuarioRepository;
import com.obras.gerenciador.domain.login.service.LoginService;
import com.obras.gerenciador.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private LoginService loginService;

    @Value("${api.security.token.cadastro}")
    private String secret;

    @PostMapping
    public ResponseEntity<ResponseToken> logar(@RequestBody @Valid RequestUsuario req){

        var dto = loginService.gerarTokenLogin(req);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Valid RequestCadastroUsuario req){
        if(!req.chave().equals(secret)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("token cadastro incorreto");
        }
        var user = new User(null, req.nome(), encoder.encode(req.senha()));
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}