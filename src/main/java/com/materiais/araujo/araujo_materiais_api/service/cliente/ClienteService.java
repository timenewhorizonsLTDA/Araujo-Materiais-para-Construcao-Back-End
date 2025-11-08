package com.materiais.araujo.araujo_materiais_api.service.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.divida.DividaResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente.ClienteNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.funcionario.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final OrcamentoRepository orcamentoRepository;
    private FuncionarioService funcionarioService;

    public ClienteService(UsuarioRepository usuarioRepository,
                          OrcamentoRepository orcamentoRepository,
                          FuncionarioService funcionarioService) {
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.funcionarioService = funcionarioService;
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

    public ResponseEntity<List<SolicitacaoProdutoResponseDTO>> verificarAgendamentos(
            StatusSolicitacao status, Integer clienteId) {

        return funcionarioService.verificarAgendamentos(
                Optional.ofNullable(status),
                Optional.ofNullable(clienteId)
        );
    }

    public ResponseEntity<List<DividaResponseDTO>> verificarDividasCliente(
            StatusDivida status, Integer clienteId) {

        return funcionarioService.verificarDividasCliente(
                Optional.ofNullable(status),
                Optional.ofNullable(clienteId)
        );
    }
}
