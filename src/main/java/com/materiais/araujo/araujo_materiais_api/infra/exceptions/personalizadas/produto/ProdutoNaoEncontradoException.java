package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto;

public class ProdutoNaoEncontradoException extends RuntimeException
{
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
