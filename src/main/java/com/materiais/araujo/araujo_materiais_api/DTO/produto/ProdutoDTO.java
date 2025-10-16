package com.materiais.araujo.araujo_materiais_api.DTO.produto;

import com.materiais.araujo.araujo_materiais_api.model.produto.TipoProduto;

public record ProdutoDTO(String nome, String codigo, Double preco, Integer quantidade, Integer estoqueMinimo, TipoProduto tipo) {
}
