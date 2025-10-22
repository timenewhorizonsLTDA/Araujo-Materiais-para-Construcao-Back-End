package com.materiais.araujo.araujo_materiais_api.DTO.agendamento;

import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoProdutoDTO(Integer clienteId, Integer orcamentoId, LocalDateTime dataHoraEntregue,
                                    String endereco, StatusSolicitacao statusSolicitacao) {
}
