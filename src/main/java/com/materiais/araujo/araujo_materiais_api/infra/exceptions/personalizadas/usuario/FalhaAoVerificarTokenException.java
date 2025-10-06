package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class FalhaAoVerificarTokenException extends RuntimeException {
    public FalhaAoVerificarTokenException() {
        super("Falha ao verificar token");
    }
}
