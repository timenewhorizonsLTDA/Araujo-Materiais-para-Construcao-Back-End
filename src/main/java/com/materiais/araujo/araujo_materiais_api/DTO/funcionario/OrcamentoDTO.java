package com.materiais.araujo.araujo_materiais_api.DTO.funcionario;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.StatusOrcamento;

import java.util.List;

public record OrcamentoDTO(String cpfCliente, List<Integer> idsProdutos, StatusOrcamento statusOrcamento) {
}
