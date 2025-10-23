package com.materiais.araujo.araujo_materiais_api.service.funcionario;

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
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.cliente.ClienteNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.gerente.SenhaInvalidaException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.EstoqueInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.PrecoInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoDuplicadoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoNaoEncontradoException;
import com.materiais.araujo.araujo_materiais_api.model.divida.Divida;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.SolicitacaoProduto;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import com.materiais.araujo.araujo_materiais_api.model.usuario.RoleUsuario;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.repository.divida.DividaRepository;
import com.materiais.araujo.araujo_materiais_api.repository.orcamento.OrcamentoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.produto.ProdutoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.solicitacaoproduto.SolicitacaoProdutoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.UtilUsuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private ProdutoRepository produtoRepository;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UtilUsuario utilUsuario;
    private OrcamentoRepository orcamentoRepository;
    private SolicitacaoProdutoRepository solicitacaoProdutoRepository;
    private DividaRepository dividaRepository;

    public FuncionarioService(ProdutoRepository produtoRepository, PasswordEncoder passwordEncoder, UtilUsuario utilUsuario,
                              UsuarioRepository usuarioRepository, OrcamentoRepository orcamentoRepository, SolicitacaoProdutoRepository solicitacaoProdutoRepository, DividaRepository dividaRepository) {
        this.produtoRepository = produtoRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilUsuario = utilUsuario;
        this.usuarioRepository = usuarioRepository;
        this.orcamentoRepository = orcamentoRepository;
        this.solicitacaoProdutoRepository = solicitacaoProdutoRepository;
        this.dividaRepository = dividaRepository;
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

    public ResponseEntity<ProdutoDTO> editarProduto(SenhaDTO senhaFuncionario, Integer idProduto, ProdutoDTO dto) {
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

        ProdutoDTO responseDTO = new ProdutoDTO(
                produtoAtualizado.getNome(),
                produtoAtualizado.getCodigo(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getQuantidade(),
                produtoAtualizado.getEstoqueMinimo(),
                produtoAtualizado.getTipo()
        );

        return ResponseEntity.ok(responseDTO);
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

    public void deletarProduto(SenhaDTO senhaFuncionario, Integer idProduto) {
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

        List<Produto> produtos = dto.nomesProdutos().stream()
                .map(nome -> (Produto) produtoRepository.findByNome(nome)
                        .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado" + nome)))
                .toList();

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

    public ResponseEntity<SolicitacaoProduto> adicionarAgendamento(SolicitacaoProdutoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: "));

        Orcamento orcamento = orcamentoRepository.findById(dto.orcamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado com ID: "));


        SolicitacaoProduto solicitacao = new SolicitacaoProduto();
        solicitacao.setCliente(cliente);
        solicitacao.setOrcamento(orcamento);
        solicitacao.setDataHoraSolicitou(LocalDateTime.now());
        solicitacao.setDataHoraEntregue(dto.dataHoraEntregue());
        solicitacao.setEndereco(dto.endereco());
        solicitacao.setStatusSolicitacao(dto.statusSolicitacao() != null
                ? dto.statusSolicitacao()
                : StatusSolicitacao.PENDENTE);


        SolicitacaoProduto salvo = solicitacaoProdutoRepository.save(solicitacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    public ResponseEntity<List<SolicitacaoProdutoResponseDTO>> verificarAgendamentos(
            Optional<StatusSolicitacao> status,
            Optional<Integer> clienteId) {

        List<SolicitacaoProduto> solicitacoes;

        if (status.isPresent() && clienteId.isPresent()) {
            solicitacoes = solicitacaoProdutoRepository.findByStatusSolicitacaoAndClienteId(status.get(), clienteId.get());
        } else if (status.isPresent()) {
            solicitacoes = solicitacaoProdutoRepository.findByStatusSolicitacao(status.get());
        } else if (clienteId.isPresent()) {
            solicitacoes = solicitacaoProdutoRepository.findByClienteId(clienteId.get());
        } else {
            solicitacoes = solicitacaoProdutoRepository.findAll();
        }

        List<SolicitacaoProdutoResponseDTO> dtos = solicitacoes.stream()
                .map(s -> new SolicitacaoProdutoResponseDTO(
                        s.getId(),
                        s.getCliente().getNome(),
                        s.getDataHoraSolicitou(),
                        s.getDataHoraEntregue(),
                        s.getEndereco(),
                        s.getStatusSolicitacao()
                ))
                .toList();

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<SolicitacaoProdutoResponseDTO> atualizarAgendamentos(
            Integer id,
            SolicitacaoProdutoAtualizacaoDTO dto) {

        SolicitacaoProduto existente = solicitacaoProdutoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Agendamento não encontrado com o ID: " + id));

        existente.setDataHoraEntregue(dto.dataHoraEntregue());
        existente.setEndereco(dto.endereco());
        existente.setStatusSolicitacao(dto.statusSolicitacao());

        SolicitacaoProduto atualizado = solicitacaoProdutoRepository.save(existente);

        SolicitacaoProdutoResponseDTO responseDTO = new SolicitacaoProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getCliente().getNome(),
                atualizado.getDataHoraSolicitou(),
                atualizado.getDataHoraEntregue(),
                atualizado.getEndereco(),
                atualizado.getStatusSolicitacao()
        );

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<DividaResponseDTO> registrarDividaCliente(Integer orcamentoId) {
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(() -> new EntityNotFoundException("Orçamento não encontrado com ID: " + orcamentoId));

        Divida novaDivida = new Divida(
                orcamento.getCliente(),
                orcamento,
                orcamento.getValorFinal(),
                LocalDateTime.now().plusDays(30),
                StatusDivida.PENDENTE
        );

        dividaRepository.save(novaDivida);

        DividaResponseDTO responseDTO = new DividaResponseDTO(
                novaDivida.getId(),
                novaDivida.getCliente().getNome(),
                novaDivida.getValor(),
                novaDivida.getDataVencimento(),
                novaDivida.getStatusDivida().toString()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    public ResponseEntity<List<DividaResponseDTO>> verificarDividasCliente(
            Optional<StatusDivida> status,
            Optional<Integer> clienteId) {

        List<Divida> dividas;

        if (status.isPresent() && clienteId.isPresent()) {
            dividas = dividaRepository.findByStatusDividaAndClienteId(status.get(), clienteId.get());
        } else if (status.isPresent()) {
            dividas = dividaRepository.findByStatusDivida(status.get());
        } else if (clienteId.isPresent()) {
            dividas = dividaRepository.findByClienteId(clienteId.get());
        } else {
            dividas = dividaRepository.findAll();
        }

        List<DividaResponseDTO> responseDTOs = dividas.stream()
                .map(divida -> new DividaResponseDTO(
                        divida.getId(),
                        divida.getCliente().getNome(),
                        divida.getValor(),
                        divida.getDataVencimento(),
                        divida.getStatusDivida().toString()
                ))
                .toList();

        return ResponseEntity.ok(responseDTOs);
    }

    public ResponseEntity<DividaResponseDTO> atualizarDividaCliente(
            SenhaDTO senhaFuncionario,
            Integer idDivida,
            DividaDTOAtualizacao dto) {

        Usuario funcionario = utilUsuario.obterUsuarioDaVez();

        if (!passwordEncoder.matches(senhaFuncionario.senha(), funcionario.getSenha())) {
            throw new SenhaInvalidaException();
        }

        Divida dividaExistente = dividaRepository.findById(idDivida)
                .orElseThrow(() -> new EntityNotFoundException("Dívida não encontrada com o ID: " + idDivida));

        if (dto.valor() != null) {
            dividaExistente.setValor(dto.valor());
        }
        if (dto.dataVencimento() != null) {
            dividaExistente.setDataVencimento(dto.dataVencimento());
        }
        if (dto.statusDivida() != null) {
            dividaExistente.setStatusDivida(dto.statusDivida());
        }

        Divida atualizada = dividaRepository.save(dividaExistente);

        DividaResponseDTO responseDTO = new DividaResponseDTO(
                atualizada.getId(),
                atualizada.getCliente().getNome(),
                atualizada.getValor(),
                atualizada.getDataVencimento(),
                atualizada.getStatusDivida().toString()
        );

        return ResponseEntity.ok(responseDTO);
    }
}