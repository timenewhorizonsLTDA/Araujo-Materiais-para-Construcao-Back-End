package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha invalida");
    }
}
