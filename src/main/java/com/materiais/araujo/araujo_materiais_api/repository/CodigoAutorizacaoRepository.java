package com.materiais.araujo.araujo_materiais_api.repository;

import com.materiais.araujo.araujo_materiais_api.model.usuario.CodigoAutorizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodigoAutorizacaoRepository extends JpaRepository<CodigoAutorizacao, Integer> {

    Optional<CodigoAutorizacao> findByCodigo(String codigo);
}
