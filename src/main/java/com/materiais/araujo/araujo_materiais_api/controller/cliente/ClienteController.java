package com.materiais.araujo.araujo_materiais_api.controller.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.service.cliente.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
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

