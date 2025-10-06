package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {


    private UsuarioRepository repository;

    public UsuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new EmailNaoEncontradoException());
    }
}
