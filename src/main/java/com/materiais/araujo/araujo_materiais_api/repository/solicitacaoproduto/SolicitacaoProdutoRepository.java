package com.materiais.araujo.araujo_materiais_api.repository.solicitacaoproduto;

import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.SolicitacaoProduto;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoProdutoRepository extends JpaRepository<SolicitacaoProduto, Integer> {
    List<SolicitacaoProduto> findByStatusSolicitacao(StatusSolicitacao statusSolicitacao);

    List<SolicitacaoProduto> findByClienteId(Integer clienteId);

    List<SolicitacaoProduto> findByStatusSolicitacaoAndClienteId(StatusSolicitacao statusSolicitacao, Integer clienteId);
}
