package com.materiais.araujo.araujo_materiais_api.repository.produto;

import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByCodigo(String codigo);

    Optional<Produto> findByNome(String nome);
}
