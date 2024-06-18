package com.obras.gerenciador.infra.security;

import com.obras.gerenciador.domain.login.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMA256(secrete);
            String token = JWT.create()
                    .withIssuer("API gerenciador.obras")
                    .withSubkect(usuario.getNome())
                    .withExpiresAt(dataExpira())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token", exception);
        }
    }

    private Object dataExpira() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
