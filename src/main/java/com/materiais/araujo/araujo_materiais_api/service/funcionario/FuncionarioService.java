package com.materiais.araujo.araujo_materiais_api.service.funcionario;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.EstoqueInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.PrecoInvalidoException;
import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.produto.ProdutoDuplicadoException;
import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.repository.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private ProdutoRepository produtoRepository;

    public FuncionarioService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto addProduto(ProdutoDTO dto) {
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

        return produtoRepository.save(produto);
    }
}
