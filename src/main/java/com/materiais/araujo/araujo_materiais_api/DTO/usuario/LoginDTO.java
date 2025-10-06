package com.materiais.araujo.araujo_materiais_api.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@Email String email
        ,@NotBlank String senha) {
}
