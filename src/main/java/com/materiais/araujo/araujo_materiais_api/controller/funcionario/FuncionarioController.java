package com.materiais.araujo.araujo_materiais_api.controller.funcionario;


import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.EstoqueStatusDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoEdicaoRequest;
import com.materiais.araujo.araujo_materiais_api.service.funcionario.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO dto) {
        return funcionarioService.cadastrarProduto(dto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProdutoDTO> editarProduto(
            @PathVariable("id") Integer id,
            @RequestBody ProdutoEdicaoRequest request) {

        return funcionarioService.editarProduto(request.senhaFuncionario(), id, request.produto());
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarProduto(
            @PathVariable("id") Integer idProduto,
            @RequestBody SenhaDTO senhaFuncionario) {

        funcionarioService.deletarProduto(senhaFuncionario, idProduto);
    }

    @GetMapping("/estoque/baixo/{nome}")
    public ResponseEntity<EstoqueStatusDTO> verificarEstoqueBaixo(@PathVariable("nome") String nome) {
        return funcionarioService.verificarEstoqueBaixo(nome);
    }
}
