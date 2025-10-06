package com.materiais.araujo.araujo_materiais_api.infra.security;

import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.service.usuario.UtilUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguranca extends OncePerRequestFilter {

    private UtilUsuario utilUsuario;

    private TokenService tokenService;

    public FiltroSeguranca(UtilUsuario utilUsuario, TokenService tokenService) {
        this.utilUsuario = utilUsuario;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = obterToken(request);

        if (token != null) {

            String email = tokenService.obterEmailUsuarioToken(token);

            Usuario usuario = utilUsuario.obterUsuarioEmail(email);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        filterChain.doFilter(request, response);


    }


    private String obterToken(HttpServletRequest request) {

        var header = request.getHeader("Authorization");

        if (header == null) return null;

        return header.replace("Bearer ", "");

    }
}
