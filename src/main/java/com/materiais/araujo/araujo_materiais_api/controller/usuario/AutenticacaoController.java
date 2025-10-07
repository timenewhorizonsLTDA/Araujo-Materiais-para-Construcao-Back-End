package com.materiais.araujo.araujo_materiais_api.controller.usuario;

import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CadastroDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.CodigoValidacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.LoginDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.usuario.TokenDTO;
import com.materiais.araujo.araujo_materiais_api.service.usuario.AutenticacaoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CadastroDTO dto) {
        return autenticacaoService.cadastrarUsuario(dto);
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
