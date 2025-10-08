package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class UsuarioNaoRealizouCadastroException extends RuntimeException {
    public UsuarioNaoRealizouCadastroException() {
        super("Usuario nao realizou cadastro");
    }
}
