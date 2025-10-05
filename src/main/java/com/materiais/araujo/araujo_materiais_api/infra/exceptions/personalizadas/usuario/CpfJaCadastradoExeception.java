package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario;

public class CpfJaCadastradoExeception extends RuntimeException{

    public CpfJaCadastradoExeception() {
        super("CPF ja existente");
    }
}
