package com.materiais.araujo.araujo_materiais_api.DTO.produto;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;

public record ProdutoEdicaoRequest(SenhaDTO senhaFuncionario, ProdutoDTO produto) {
}
