package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class CodigoDeValidacaoExpiradoException extends RuntimeException {
    public CodigoDeValidacaoExpiradoException() {
        super("Codigo expirado");
    }
}
