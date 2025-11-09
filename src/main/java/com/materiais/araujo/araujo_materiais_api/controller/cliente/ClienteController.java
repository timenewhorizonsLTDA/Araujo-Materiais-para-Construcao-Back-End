package com.materiais.araujo.araujo_materiais_api.controller.cliente;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.service.cliente.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> consultarProdutosCliente(@RequestParam String cpf) {
        List<ProdutoDTO> produtos = clienteService.consultarProdutosCliente(cpf);
        return ResponseEntity.ok(produtos);
    }
}

