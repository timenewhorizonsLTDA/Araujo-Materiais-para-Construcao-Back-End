package com.materiais.araujo.araujo_materiais_api.repository.divida;

import com.materiais.araujo.araujo_materiais_api.model.divida.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividaRepository extends JpaRepository<Divida, Integer> {
}
