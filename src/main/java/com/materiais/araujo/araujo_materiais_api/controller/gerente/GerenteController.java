package com.materiais.araujo.araujo_materiais_api.controller.gerente;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiais.araujo.araujo_materiais_api.DTO.gerente.BuscarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.BuscarFuncionarioNomeDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.CadastrarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.EditarFuncionarioDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.EditarFuncionarioResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;
import com.materiais.araujo.araujo_materiais_api.service.gerente.GerenteService;

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
    public ResponseEntity<EditarFuncionarioResponseDTO> editarFuncionario(@PathVariable(name = "id") Integer id, @RequestBody EditarFuncionarioDTO dto){
        return gerenteService.editarFuncionario(id, dto);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BuscarFuncionarioDTO>> buscarTodosFuncionarios(){
        return gerenteService.buscarTodosFuncionarios();
    }

    @GetMapping("/buscar/nome")
    public ResponseEntity<List<BuscarFuncionarioDTO>> buscarFuncionarioPorNome(@RequestBody BuscarFuncionarioNomeDTO dto){
        return gerenteService.buscarFuncionariosPorNome(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarFuncionario(@RequestBody SenhaDTO dto, @PathVariable(name = "id") Integer id){
        gerenteService.deletarFuncionario(dto, id);
    }
}
