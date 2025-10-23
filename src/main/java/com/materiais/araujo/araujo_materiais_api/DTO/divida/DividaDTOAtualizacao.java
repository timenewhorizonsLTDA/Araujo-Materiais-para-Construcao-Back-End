package com.materiais.araujo.araujo_materiais_api.DTO.divida;

import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;

import java.time.LocalDateTime;

public record DividaDTOAtualizacao(Double valor, LocalDateTime dataVencimento, StatusDivida statusDivida) {
}
