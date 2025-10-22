package com.materiais.araujo.araujo_materiais_api.DTO.divida;

import java.time.LocalDateTime;

public record DividaResponseDTO(
        Integer id,
        String nomeCliente,
        Double valor,
        LocalDateTime dataVencimento,
        String statusDivida
) {

}
