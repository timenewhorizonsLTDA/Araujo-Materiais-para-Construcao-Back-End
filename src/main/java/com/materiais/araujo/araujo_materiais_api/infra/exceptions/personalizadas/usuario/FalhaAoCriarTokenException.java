package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class FalhaAoCriarTokenException extends RuntimeException {
    public FalhaAoCriarTokenException() {
        super("Falha ao criar token");
    }
}
