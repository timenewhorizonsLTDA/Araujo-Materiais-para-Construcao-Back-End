package com.materiais.araujo.araujo_materiais_api.DTO.usuario;

import jakarta.validation.constraints.NotBlank;

public record CodigoValidacaoDTO(@NotBlank String codigo) {
}
