package com.materiais.araujo.araujo_materiais_api.DTO.funcionario;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.StatusOrcamento;
import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record OrcamentoDTO(Usuario cliente, List<Produto> produtos, LocalDateTime dataEmissao, Double valorFinal, StatusOrcamento statusOrcamento) {
}
