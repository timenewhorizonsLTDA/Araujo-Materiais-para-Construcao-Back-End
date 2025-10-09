package com.materiais.araujo.araujo_materiais_api.DTO.gerente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record CadastrarFuncionarioDTO(@NotBlank String nome, @CPF String cpf, @Email String email) {
}
