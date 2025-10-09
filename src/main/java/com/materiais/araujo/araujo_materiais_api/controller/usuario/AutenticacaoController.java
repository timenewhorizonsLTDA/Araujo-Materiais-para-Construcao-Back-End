package com.materiais.araujo.araujo_materiais_api.controller.usuario;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.*;
import com.materiais.araujo.araujo_materiais_api.service.usuario.AutenticacaoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
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


    @PutMapping
    public ResponseEntity<String> validarUsuario(@RequestBody @Valid CodigoValidacaoDTO dto) {
        return autenticacaoService.validarUsuario(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginUsuario(@RequestBody @Valid LoginDTO dto) {
        return autenticacaoService.login(dto);
    }
}
