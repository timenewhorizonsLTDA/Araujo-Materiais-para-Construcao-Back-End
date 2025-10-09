package com.materiais.araujo.araujo_materiais_api.controller.gerente;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.service.gerente.GerenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gerente")
public class GerenteController {

    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    @PostMapping
    public ResponseEntity<CadastrarFuncionarioResponseDTO> cadastrarFuncionario(@RequestBody CadastrarFuncionarioDTO dto){
        return gerenteService.cadastrarFuncionario(dto);
    }
}
