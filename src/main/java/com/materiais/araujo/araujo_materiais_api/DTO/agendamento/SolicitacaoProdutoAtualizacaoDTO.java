package com.materiais.araujo.araujo_materiais_api.DTO.agendamento;

import com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoProdutoAtualizacaoDTO(LocalDateTime dataHoraEntregue, String endereco, StatusSolicitacao statusSolicitacao) {
}
