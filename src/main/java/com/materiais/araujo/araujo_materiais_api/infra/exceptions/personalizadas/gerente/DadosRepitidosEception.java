package com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente;

public class DadosRepitidosEception extends RuntimeException {
    public DadosRepitidosEception() {
        super("Campo ja cadastrado");
    }
}
