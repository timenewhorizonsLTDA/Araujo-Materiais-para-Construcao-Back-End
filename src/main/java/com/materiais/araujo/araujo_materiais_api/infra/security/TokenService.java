package com.materiais.araujo.araujo_materiais_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${seguranca.senha}")
    private String minhaSenha;


    public String gerarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(minhaSenha);

            return JWT.create()
                    .withIssuer("araujo-construcao-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoToken())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String obterEmailUsuarioToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(minhaSenha);

            return JWT.require(algorithm)
                    .withIssuer("araujo-construcao-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    private Instant tempoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
