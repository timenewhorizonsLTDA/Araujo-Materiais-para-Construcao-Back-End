package com.materiais.araujo.araujo_materiais_api.repository.produto;

import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
