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
public class TesteControler {

    private AutenticacaoService autenticacaoService;

    public TesteControler(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<String> sla(@RequestBody @Valid CadastroDTO dto) {
        return autenticacaoService.cadastrarUsuario(dto);
    }

    @PutMapping
    public ResponseEntity<String> sla2(@RequestBody @Valid CodigoValidacaoDTO dto) {
        return autenticacaoService.validarUsuario(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> sla3(@RequestBody @Valid LoginDTO dto) {
        return autenticacaoService.login(dto);
    }
}
