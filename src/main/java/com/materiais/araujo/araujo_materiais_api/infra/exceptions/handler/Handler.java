package com.materiais.araujo.araujo_materiais_api.infra.exceptions.handler;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.CpfJaCadastradoExeception;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailJaCadastradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.EmailNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.FalhaAoEnviarEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> EmailNaoEncontradoHandler(EmailNaoEncontradoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<RespostaErro> EmailJaCadastradoHandler(EmailJaCadastradoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(CpfJaCadastradoExeception.class)
    public ResponseEntity<RespostaErro> CpfJaCadastradoHandler(CpfJaCadastradoExeception e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(FalhaAoEnviarEmailException.class)
    public ResponseEntity<RespostaErro> FalhaAoEnviarEmailHandler(FalhaAoEnviarEmailException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

}
