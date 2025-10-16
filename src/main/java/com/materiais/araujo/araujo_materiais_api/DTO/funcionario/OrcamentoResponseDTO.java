package com.materiais.araujo.araujo_materiais_api.DTO.funcionario;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.StatusOrcamento;

import java.time.LocalDateTime;
import java.util.List;

public record OrcamentoResponseDTO(Integer id, String nomeCliente, List<String> produtos, Double valorTotal, LocalDateTime dataEmissao, StatusOrcamento status) {
}
