package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException() {
        super("Email ja existente");
    }
}
