package com.materiais.araujo.araujo_materiais_api.repository.orcamento;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
}
