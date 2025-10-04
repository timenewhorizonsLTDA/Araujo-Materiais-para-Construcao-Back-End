package com.materiais.araujo.araujo_materiais_api.infra.exceptions.handler;

import org.springframework.http.HttpStatus;

public class RespostaErro {

    private HttpStatus status;

    private String mensagem;

    public RespostaErro(HttpStatus status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
