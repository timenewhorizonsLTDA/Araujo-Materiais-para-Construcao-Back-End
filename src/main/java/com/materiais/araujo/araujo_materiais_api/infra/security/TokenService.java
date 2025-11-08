package com.materiais.araujo.araujo_materiais_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.FalhaAoCriarTokenException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.FalhaAoVerificarTokenException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${seguranca.senha}")
    private String minhaSenha;


    public String gerarToken(Usuario usuario) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(minhaSenha);

            List<String> roles = usuario.getAuthorities()
                    .stream()
                    .map(a -> a.getAuthority())
                    .collect(Collectors.toList());

            return JWT.create()
                    .withIssuer("araujo-construcao-api")
                    .withSubject(usuario.getEmail())
                    .withClaim("roles",roles)
                    .withExpiresAt(tempoToken())
                    .sign(algorithm);
        } catch (RuntimeException e) {
            throw new FalhaAoCriarTokenException();
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

        } catch (RuntimeException e) {
            throw new FalhaAoVerificarTokenException();
        }


    }


    private Instant tempoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
