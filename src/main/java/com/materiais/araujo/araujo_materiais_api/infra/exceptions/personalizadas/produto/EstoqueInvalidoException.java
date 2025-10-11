package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto;

public class EstoqueInvalidoException extends RuntimeException {
    public EstoqueInvalidoException(String message) {
        super(message);
    }
}
