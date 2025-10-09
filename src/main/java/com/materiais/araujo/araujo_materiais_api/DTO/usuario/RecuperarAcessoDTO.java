package com.materiais.araujo.araujo_materiais_api.DTO.usuario;

import jakarta.validation.constraints.Email;

public record RecuperarAcessoDTO(@Email String email) {
}
