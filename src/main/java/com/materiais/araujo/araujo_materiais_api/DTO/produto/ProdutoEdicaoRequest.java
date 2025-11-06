package com.materiais.araujo.araujo_materiais_api.DTO.produto;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;
import com.materiais.araujo.araujo_materiais_api.model.produto.TipoProduto;

public record ProdutoEdicaoRequest(String senha, String nome, String codigo, Double preco,
                                   Integer quantidade, Integer estoqueMinimo, TipoProduto tipo) {
}
