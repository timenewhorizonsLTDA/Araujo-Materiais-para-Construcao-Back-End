package com.materiais.araujo.araujo_materiais_api.repository.divida;

import com.materiais.araujo.araujo_materiais_api.model.divida.Divida;
import com.materiais.araujo.araujo_materiais_api.model.divida.StatusDivida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Integer> {
    List<Divida> findByStatusDivida(StatusDivida statusDivida);
    List<Divida> findByClienteId(Integer clienteId);
    List<Divida> findByStatusDividaAndClienteId(StatusDivida statusDivida, Integer clienteId);
}
