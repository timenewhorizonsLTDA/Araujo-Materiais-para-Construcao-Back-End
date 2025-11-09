package com.materiais.araujo.araujo_materiais_api.controller.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.cliente.ClienteAtualizacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.cliente.ClienteResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.divida.DividaResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import com.materiais.araujo.araujo_materiais_api.service.cliente.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteResponseDTO> editarFuncionario(@PathVariable(name = "id") Integer id, @RequestBody ClienteAtualizacaoDTO dto){
        return clienteService.atualizarDados(id, dto);
    }

    @GetMapping("/orcamento/{id}")
    public ResponseEntity<List<ProdutoDTO>> consultarProdutosDoOrcamento(@PathVariable Integer id) {
        return clienteService.consultarProdutosDoOrcamento(id);
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<List<SolicitacaoProdutoResponseDTO>> verificarAgendamentosCliente(
            @RequestParam(required = false) StatusSolicitacao status,
            @RequestParam(required = false) Integer clienteId) {

        return clienteService.verificarAgendamentos(status, clienteId);
    }

    @GetMapping("/dividas")
    public ResponseEntity<List<DividaResponseDTO>> verificarDividasCliente(
            @RequestParam(required = false) StatusDivida status,
            @RequestParam(required = false) Integer clienteId) {

        return clienteService.verificarDividasCliente(status, clienteId);
    }

}

