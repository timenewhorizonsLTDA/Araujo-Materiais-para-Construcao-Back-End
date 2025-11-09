package com.materiais.araujo.araujo_materiais_api.DTO.cliente;

import com.materiais.araujo.araujo_materiais_api.model.usuario.StatusUsuario;

public record ClienteResponseDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        StatusUsuario statusUsuario) {
}
