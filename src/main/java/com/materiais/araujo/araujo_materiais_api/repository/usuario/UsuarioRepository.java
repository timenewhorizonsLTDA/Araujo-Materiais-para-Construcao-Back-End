package com.materiais.araujo.araujo_materiais_api.repository.usuario;

import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    List<Usuario> findAllByRole(RoleUsuario roleUsuario);

    List<Usuario> findByRoleAndNomeContainingIgnoreCase(RoleUsuario role, String nome);

    Optional<Usuario> findByIdAndRole(Integer id, RoleUsuario roleUsuario);
}
