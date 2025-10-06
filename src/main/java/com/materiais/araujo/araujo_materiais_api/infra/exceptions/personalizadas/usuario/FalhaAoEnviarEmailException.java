package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class FalhaAoEnviarEmailException extends RuntimeException{

    public FalhaAoEnviarEmailException() {
        super("Falha ao enviar Email");
    }
}
