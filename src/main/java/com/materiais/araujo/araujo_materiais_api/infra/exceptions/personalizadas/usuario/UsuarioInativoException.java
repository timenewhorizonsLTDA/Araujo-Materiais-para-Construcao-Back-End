package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class UsuarioInativoException extends RuntimeException {
    public UsuarioInativoException() {
        super("É necessario realizar a validacao de email");
    }
}
