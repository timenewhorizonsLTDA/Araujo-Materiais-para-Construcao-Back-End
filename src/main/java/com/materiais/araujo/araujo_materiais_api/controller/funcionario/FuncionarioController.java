package com.materiais.araujo.araujo_materiais_api.controller.funcionario;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoAtualizacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.divida.DividaDTOAtualizacao;
import com.materiais.araujo.araujo_materiais_api.DTO.divida.DividaResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.funcionario.OrcamentoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.funcionario.OrcamentoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.EstoqueStatusDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoEdicaoRequest;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.SolicitacaoProduto;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import com.materiais.araujo.araujo_materiais_api.service.funcionario.FuncionarioService;

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
    public ResponseEntity<ProdutoDTO> editarProduto(@PathVariable("id") Integer id, @RequestBody ProdutoEdicaoRequest request) {

        return funcionarioService.editarProduto(id, request);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarProduto(@PathVariable("id") Integer idProduto, @RequestBody SenhaDTO senha) {

        funcionarioService.deletarProduto(senha, idProduto);
    }

    @GetMapping("/produto")
    public ResponseEntity<ProdutoDTO> consultarProdutoPorNome(@RequestParam(name = "nome") String nome) {
        return funcionarioService.consultarProdutoPorNome(nome);
    }

    @GetMapping("/estoque/baixo")
    public ResponseEntity<EstoqueStatusDTO> verificarEstoqueBaixo(@RequestParam(name = "nome") String nome) {
        return funcionarioService.verificarEstoqueBaixo(nome);
    }

    @PostMapping("/orcamento")
    public ResponseEntity<OrcamentoResponseDTO> fazerOrcamento(@RequestBody OrcamentoDTO dto) {
        return funcionarioService.fazerOrcamento(dto);
    }

    @PostMapping("/agendamento")
    public ResponseEntity<SolicitacaoProduto> adicionarAgendamento(@RequestBody SolicitacaoProdutoDTO dto) {
        return funcionarioService.adicionarAgendamento(dto);
    }

    @GetMapping("/agendamento")
    public ResponseEntity<List<SolicitacaoProdutoResponseDTO>> verificarAgendamentosPorStatus(
            @RequestParam StatusSolicitacao status) {
        return funcionarioService.verificarAgendamentos(
                Optional.of(status),
                Optional.empty()
        );
    }

    @PutMapping("/agendamento/{id}")
    public ResponseEntity<SolicitacaoProdutoResponseDTO> atualizarAgendamento(
            @PathVariable Integer id,
            @RequestBody SolicitacaoProdutoAtualizacaoDTO dto) {
        return funcionarioService.atualizarAgendamentos(id, dto);
    }

    @PostMapping("/registrar/divida/{id}")
    public ResponseEntity<Void> registrarDivida(@PathVariable("id") Integer orcamentoId) {
        funcionarioService.registrarDividaCliente(orcamentoId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verificar/divida")
    public ResponseEntity<List<DividaResponseDTO>> verificarDividas(
            @RequestParam Optional<StatusDivida> status,
            @RequestParam Optional<Integer> clienteId) {

        return funcionarioService.verificarDividasCliente(status, clienteId);
    }

    @PutMapping("/atualizar/divida/{id}")
    public ResponseEntity<Void> atualizarDivida(
            @PathVariable("id") Integer idDivida,
            @RequestBody DividaDTOAtualizacao dto) {

        funcionarioService.atualizarDividaCliente(idDivida, dto);
        
        return ResponseEntity.ok().build();
    }
}
