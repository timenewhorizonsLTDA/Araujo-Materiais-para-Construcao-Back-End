package com.materiais.araujo.araujo_materiais_api.service.funcionario;

import com.materiais.araujo.araujo_materiais_api.DTO.funcionario.OrcamentoDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.funcionario.OrcamentoResponseDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.gerente.SenhaDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.EstoqueStatusDTO;
import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente.ClienteNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.SenhaInvalidaException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.EstoqueInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.PrecoInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoDuplicadoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.produto.ProdutoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.UtilUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuncionarioService {
    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UtilUsuario utilUsuario;
    private OrcamentoRepository orcamentoRepository;

    public FuncionarioService(ProdutoRepository produtoRepository, PasswordEncoder passwordEncoder, UtilUsuario utilUsuario,
                              UsuarioRepository usuarioRepository, OrcamentoRepository orcamentoRepository) {
        this.produtoRepository = produtoRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilUsuario = utilUsuario;
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
    }

    public ResponseEntity<ProdutoDTO> cadastrarProduto(ProdutoDTO dto) {
        if (produtoRepository.existsByCodigo(dto.codigo())) {
            throw new ProdutoDuplicadoException("Já existe um produto com o código " + dto.codigo());
        }

        if (dto.preco() <= 0) {
            throw new PrecoInvalidoException("O preço deve ser maior que zero.");
        }

        if (dto.quantidade() < 0) {
            throw new EstoqueInvalidoException("A quantidade inicial não pode ser negativa.");
        }

        if (dto.estoqueMinimo() < 0) {
            throw new EstoqueInvalidoException("O estoque mínimo não pode ser negativo.");
        }

        if (dto.quantidade() < dto.estoqueMinimo()) {
            throw new EstoqueInvalidoException("A quantidade inicial não pode ser menor que o estoque mínimo.");
        }

        Produto produto = new Produto(
                dto.nome(),
                dto.codigo(),
                dto.preco(),
                dto.quantidade(),
                dto.estoqueMinimo(),
                dto.tipo()
        );

        produtoRepository.save(produto);

        return ResponseEntity.ok().body(dto);
    }

    public ProdutoDTO editarProduto(SenhaDTO senhaFuncionario, Integer idProduto, ProdutoDTO dto) {
        Usuario funcionario = utilUsuario.obterUsuarioDaVez();

        if (!passwordEncoder.matches(senhaFuncionario.senha(), funcionario.getSenha())) {
            throw new SenhaInvalidaException();
        }

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        produto.setNome(dto.nome());
        produto.setCodigo(dto.codigo());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        produto.setTipo(dto.tipo());

        Produto produtoAtualizado = produtoRepository.save(produto);

        return new ProdutoDTO(
                produtoAtualizado.getNome(),
                produtoAtualizado.getCodigo(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getQuantidade(),
                produtoAtualizado.getEstoqueMinimo(),
                produtoAtualizado.getTipo()
        );
    }
    public ResponseEntity<ProdutoDTO> consultarProdutoPorNome(String nome) {
        Produto produto = (Produto) produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        ProdutoDTO dto = new ProdutoDTO(
                produto.getNome(),
                produto.getCodigo(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getEstoqueMinimo(),
                produto.getTipo()
        );

        return ResponseEntity.ok().body(dto);
    }

    public void deletarProduto(SenhaDTO senhaFuncionario, Integer idProduto, ProdutoDTO dto) {
        Usuario funcionario = utilUsuario.obterUsuarioDaVez();

        if (!passwordEncoder.matches(senhaFuncionario.senha(), funcionario.getSenha())) {
            throw new SenhaInvalidaException();
        }

        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RuntimeException());

        produtoRepository.delete(produto);
    }

    public ResponseEntity<EstoqueStatusDTO> verificarEstoqueBaixo(String nome) {
        Produto produto = (Produto) produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        String statusEstoque = produto.getQuantidade() < produto.getEstoqueMinimo()
                ? "baixo"
                : "normal";

        EstoqueStatusDTO resposta = new EstoqueStatusDTO(
                produto.getNome(),
                produto.getQuantidade(),
                produto.getEstoqueMinimo(),
                statusEstoque
        );

        return ResponseEntity.ok(resposta);
    }
    public ResponseEntity<OrcamentoResponseDTO> fazerOrcamento(OrcamentoDTO dto) {

        Usuario cliente = usuarioRepository.findByCpf(dto.cpfCliente())
                .filter(u -> u.getRole() == RoleUsuario.CLIENTE)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com CPF: " + dto.cpfCliente()));

        List<Produto> produtos = produtoRepository.findAllById(dto.idsProdutos());
        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Nenhum produto encontrado para os IDs informados.");
        }

        Double valorFinal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        Orcamento orcamento = new Orcamento(
                cliente,
                produtos,
                LocalDateTime.now(),
                valorFinal,
                dto.statusOrcamento()
        );

        return ResponseEntity.ok(
                new OrcamentoResponseDTO(
                        orcamento.getId(),
                        cliente.getNome(),
                        produtos.stream().map(Produto::getNome).toList(),
                        valorFinal,
                        orcamento.getDataEmissao(),
                        orcamento.getStatusOrcamento()
                )
        );
    }
    
}
