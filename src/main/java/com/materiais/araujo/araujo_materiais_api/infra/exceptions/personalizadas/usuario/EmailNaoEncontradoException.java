package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class EmailNaoEncontradoException extends RuntimeException{

    public EmailNaoEncontradoException() {
        super("Email nao encontrado");
    }
}
