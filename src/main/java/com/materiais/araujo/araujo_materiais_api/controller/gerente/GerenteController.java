package com.materiais.araujo.araujo_materiais_api.controller.gerente;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.EditarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.EditarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.service.gerente.GerenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gerente")
public class GerenteController {

    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CadastrarFuncionarioResponseDTO> cadastrarFuncionario(@RequestBody CadastrarFuncionarioDTO dto){
        return gerenteService.cadastrarFuncionario(dto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EditarFuncionarioResponseDTO> editarFuncionario(@PathVariable Integer id, @RequestBody EditarFuncionarioDTO dto){
        return gerenteService.editarFuncionario(id, dto);
    }
}
