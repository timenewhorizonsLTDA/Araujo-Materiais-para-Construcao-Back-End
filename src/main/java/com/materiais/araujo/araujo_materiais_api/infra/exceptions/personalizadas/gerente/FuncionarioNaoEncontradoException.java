package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException() {
        super("Funcionario nao encontrado");
    }
}
