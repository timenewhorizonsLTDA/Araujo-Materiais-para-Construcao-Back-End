package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto;

public class ProdutoDuplicadoException extends RuntimeException {
    public ProdutoDuplicadoException(String message) {
        super(message);
    }
}
