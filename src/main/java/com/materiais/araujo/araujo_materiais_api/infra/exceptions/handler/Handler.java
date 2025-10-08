package com.materiais.araujo.araujo_materiais_api.infra.exceptions.handler;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(CodigoDeValidacaoNaoValidoException.class)
    public ResponseEntity<RespostaErro> CodigoDeValidacaoNaoValidoHandler(CodigoDeValidacaoNaoValidoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(CodigoDeValidacaoExpiradoException.class)
    public ResponseEntity<RespostaErro> CodigoDeValidacaoExpiradoHandler(CodigoDeValidacaoExpiradoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RespostaErro> BadCredentialsHandler(BadCredentialsException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(UsuarioInativoException.class)
    public ResponseEntity<RespostaErro> UsuarioInativoHandler(UsuarioInativoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }


    @ExceptionHandler(FalhaAoCriarTokenException.class)
    public ResponseEntity<RespostaErro> FalhaAoCriarTokenHandler(FalhaAoCriarTokenException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(FalhaAoVerificarTokenException.class)
    public ResponseEntity<RespostaErro> FalhaAoVerificarTokenHandler(FalhaAoVerificarTokenException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(UsuarioNaoRealizouCadastroException.class)
    public ResponseEntity<RespostaErro> UsuarioNaoRealizouCadastroHandler(UsuarioNaoRealizouCadastroException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }


}
