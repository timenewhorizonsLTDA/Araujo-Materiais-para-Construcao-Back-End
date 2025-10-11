package com.materiais.araujo.araujo_materiais_api.repository.produto;

import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    boolean existsByCodigo(String codigo);

    <T> ScopedValue<T> findByNome(String nome);
}
