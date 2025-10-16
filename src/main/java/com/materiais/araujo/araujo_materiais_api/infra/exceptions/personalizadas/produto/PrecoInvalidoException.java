package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto;

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException(String message) {
        super(message);
    }
}
