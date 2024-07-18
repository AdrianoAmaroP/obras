package com.obras.gerenciador.infra.security;

import com.obras.gerenciador.domain.login.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = pegarToken(request);

        if(token != null ){
            var userName =  tokenService.getSubject(token);
            var user = usuarioRepository.findByNome(userName).orElseThrow(
                    () -> new RuntimeException("Usuário não encontrado")
            );

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }


        filterChain.doFilter(request, response);
    }

    private String pegarToken(HttpServletRequest request) throws RuntimeException {

        var token = request.getHeader("Authorization");

        if(token != null){
            return token.replace("Bearer ", "");
        }

        return null;

    }
}
