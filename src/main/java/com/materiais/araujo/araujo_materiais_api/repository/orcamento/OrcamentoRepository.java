package com.materiais.araujo.araujo_materiais_api.repository.orcamento;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
}
