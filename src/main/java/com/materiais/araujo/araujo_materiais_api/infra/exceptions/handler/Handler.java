package com.materiais.araujo.araujo_materiais_api.infra.exceptions.handler;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.DadosRepitidosEception;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioJaExistenteException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.EstoqueInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.PrecoInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoDuplicadoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.FuncionarioNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.SenhaInvalidaException;
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

    @ExceptionHandler(FuncionarioJaExistenteException.class)
    public ResponseEntity<RespostaErro> FuncionarioJaExistenteHandler(FuncionarioJaExistenteException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> FuncionarioNaoEncontradoHandler(FuncionarioNaoEncontradoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(DadosRepitidosEception.class)
    public ResponseEntity<RespostaErro> DadosRepitidoshandler(DadosRepitidosEception e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<RespostaErro> SenhaInvalidaHandler(SenhaInvalidaException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    public ResponseEntity<RespostaErro> produtoDuplicadoHandler(ProdutoDuplicadoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> produtoNaoEncontradoHandler(ProdutoNaoEncontradoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(EstoqueInvalidoException.class)
    public ResponseEntity<RespostaErro> estoqueInvalidoHandler(EstoqueInvalidoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }

    @ExceptionHandler(PrecoInvalidoException.class)
    public ResponseEntity<RespostaErro> precoInvalidoHandler(PrecoInvalidoException e) {
        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(respostaErro.getStatus()).body(respostaErro);
    }
}
