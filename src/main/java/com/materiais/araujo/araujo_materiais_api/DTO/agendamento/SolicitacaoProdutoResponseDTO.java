package com.materiais.araujo.araujo_materiais_api.DTO.agendamento;


import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.SolicitacaoProduto;
import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoProdutoResponseDTO(
        Integer id,
        String nomeCliente,
        LocalDateTime dataHoraSolicitou,
        LocalDateTime dataHoraEntregue,
        String endereco,
        StatusSolicitacao statusSolicitacao
) {

}
