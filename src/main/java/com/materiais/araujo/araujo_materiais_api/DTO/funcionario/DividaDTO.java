package com.materiais.araujo.araujo_materiais_api.DTO.funcionario;

import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;

import java.time.LocalDateTime;

public record DividaDTO(Usuario cliente, Orcamento orcamento, Double valor, LocalDateTime dataVencimento, StatusDivida statusDivida) {
}
