package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente;

public class FuncionarioJaExistenteException extends RuntimeException {
    public FuncionarioJaExistenteException() {
        super("Funcionario ja existente");
    }
}
