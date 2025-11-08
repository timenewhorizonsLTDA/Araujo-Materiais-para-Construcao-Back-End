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

    @GetMapping("/orcamento/{id}")
    public ResponseEntity<List<ProdutoDTO>> consultarProdutosDoOrcamento(@PathVariable Integer id) {
        return clienteService.consultarProdutosDoOrcamento(id);
    }

}

