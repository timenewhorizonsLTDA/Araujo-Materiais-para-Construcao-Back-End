package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
