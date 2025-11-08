package com.materiais.araujo.araujo_materiais_api.repository.orcamento;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    List<Orcamento> findByCliente(Usuario cliente);
}
