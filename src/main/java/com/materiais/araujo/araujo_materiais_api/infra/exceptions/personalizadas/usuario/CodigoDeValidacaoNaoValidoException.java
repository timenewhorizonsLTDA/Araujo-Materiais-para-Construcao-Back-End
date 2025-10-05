package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class CodigoDeValidacaoNaoValidoException extends RuntimeException{

    public CodigoDeValidacaoNaoValidoException() {
        super("Codigo de validacao invalido");
    }
}
