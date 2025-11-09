package com.materiais.araujo.araujo_materiais_api.controller.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CodigoValidacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.LoginDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.RecuperarAcessoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.TokenDTO;
import com.materiais.araujo.araujo_materiais_api.service.usuario.AutenticacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponseDTO> cadastrarUsuario(@RequestBody @Valid CadastroDTO dto) {
        return autenticacaoService.cadastrarUsuario(dto);
    }

    @PutMapping("/reenviar/{id}")
    public ResponseEntity<String> reenviarCodigo(@PathVariable("id") Integer usuarioID){
        return autenticacaoService.reenviarCodigo(usuarioID);
    }

    @PutMapping("/recuperaracesso")
    public ResponseEntity<String> recuperarAcesso(@RequestBody @Valid RecuperarAcessoDTO dto){
        return autenticacaoService.recuperarAcesso(dto);
    }


    @PutMapping("/validar")
    public ResponseEntity<String> validarUsuario(@RequestBody @Valid CodigoValidacaoDTO dto) {
        return autenticacaoService.validarUsuario(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginUsuario(@RequestBody @Valid LoginDTO dto) {
        return autenticacaoService.login(dto);
    }
}
