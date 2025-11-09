package com.materiais.araujo.araujo_materiais_api.service.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.agendamento.SolicitacaoProdutoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.cliente.ClienteAtualizacaoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.cliente.ClienteResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.divida.DividaResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.funcionario.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class ClienteService {

    private UsuarioRepository usuarioRepository;
    private OrcamentoRepository orcamentoRepository;
    private FuncionarioService funcionarioService;
    private PasswordEncoder passwordEncoder;

    public ClienteService(UsuarioRepository usuarioRepository,
                          OrcamentoRepository orcamentoRepository,
                          FuncionarioService funcionarioService,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.funcionarioService = funcionarioService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<ClienteResponseDTO> atualizarDados(Integer id, ClienteAtualizacaoDTO dto) {
        Usuario cliente = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: "));

        if (!dto.nome().isEmpty()) {
            cliente.setNome(dto.nome());
        }
        if (!dto.email().isEmpty()) {
            cliente.setEmail(dto.email());
        }
        if (!dto.contato().isEmpty()) {
            cliente.setTelefone(dto.contato());
        }
        if (!dto.senha().isEmpty()) {
            cliente.setSenha(passwordEncoder.encode(dto.senha()));
        }

        usuarioRepository.save(cliente);

        ClienteResponseDTO responseDTO = new ClienteResponseDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getStatusUsuario()
        );

        return ResponseEntity.ok(responseDTO);
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
