package com.materiais.araujo.araujo_materiais_api.service.cliente;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente.ClienteNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final OrcamentoRepository orcamentoRepository;

    public ClienteService(UsuarioRepository usuarioRepository,
                          OrcamentoRepository orcamentoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
    }

    public List<ProdutoDTO> consultarProdutosCliente(String cpfCliente) {
        var cliente = usuarioRepository.findByCpf(cpfCliente)
                .filter(u -> u.getRole() == RoleUsuario.CLIENTE)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException("Cliente não encontrado com CPF: " + cpfCliente));

        var orcamentos = orcamentoRepository.findByCliente(cliente);

        if (orcamentos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para este cliente.");
        }

        return orcamentos.stream()
                .flatMap(o -> o.getProdutos().stream())
                .distinct()
                .map(p -> new ProdutoDTO(
                        p.getNome(),
                        p.getCodigo(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getEstoqueMinimo(),
                        p.getTipo()
                ))
                .collect(Collectors.toList());
    }
}
