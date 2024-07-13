package com.obras.gerenciador.domain.login.service;

import com.obras.gerenciador.domain.login.entities.RequestUsuario;
import com.obras.gerenciador.domain.login.entities.ResponseToken;
import com.obras.gerenciador.domain.login.entities.Usuario;
import com.obras.gerenciador.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    public ResponseToken gerarTokenLogin(RequestUsuario req){
        try {
            var token = new UsernamePasswordAuthenticationToken(req.nome(), req.senha());
            var authentication = manager.authenticate(token);
            var dto = new ResponseToken(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
            return dto;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário inexistente ou senha inválida");
        }
    }
}
