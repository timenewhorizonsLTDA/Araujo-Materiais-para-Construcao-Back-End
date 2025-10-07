package com.materiais.araujo.araujo_materiais_api.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroDTO(@NotBlank String nome
        ,@NotBlank @CPF String cpf
        ,@Email String email
        ,@NotBlank String senha) {
}
