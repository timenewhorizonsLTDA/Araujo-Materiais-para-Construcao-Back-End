package com.materiais.araujo.araujo_materiais_api.service.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente.ClienteNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final OrcamentoRepository orcamentoRepository;

    public ClienteService(UsuarioRepository usuarioRepository,
                          OrcamentoRepository orcamentoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
    }

    @Transactional
    public ResponseEntity<List<ProdutoDTO>> consultarProdutosDoOrcamento(Integer orcamentoId) {
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado com ID: " + orcamentoId));

        List<ProdutoDTO> produtos = orcamento.getProdutos().stream()
                .map(p -> new ProdutoDTO(
                        p.getNome(),
                        p.getCodigo(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getEstoqueMinimo(),
                        p.getTipo()
                ))
                .collect(toList());

        return ResponseEntity.ok(produtos);
    }

}
