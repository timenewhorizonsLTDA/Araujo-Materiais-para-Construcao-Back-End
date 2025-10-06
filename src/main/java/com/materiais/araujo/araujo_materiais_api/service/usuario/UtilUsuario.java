package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UtilUsuario {


    private UsuarioRepository usuarioRepository;

    public UtilUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario obterUsuarioEmail(String email) {

        return usuarioRepository.findByEmail(email).orElseThrow(() -> new EmailNaoEncontradoException());
    }

    public Usuario obterUsuarioDaVez() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return obterUsuarioEmail(email);
    }


}
