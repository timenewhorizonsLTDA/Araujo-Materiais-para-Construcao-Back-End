package com.materiais.araujo.araujo_materiais_api.repository.solicitacaoproduto;

import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.SolicitacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoProdutoRepository extends JpaRepository<SolicitacaoProduto, Integer> {
}
