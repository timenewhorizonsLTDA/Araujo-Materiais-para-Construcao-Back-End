package com.materiais.araujo.araujo_materiais_api.model.solicitacaoproduto;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SolicitacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario cliente;

    @OneToOne
    @JoinColumn(nullable = false)
    private Orcamento orcamento;

    @Column(nullable = false)
    private LocalDateTime dataHoraSolicitou;

    @Column(nullable = false)
    private LocalDateTime dataHoraEntregue;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String contato;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;

    public SolicitacaoProduto() {
    }

    public SolicitacaoProduto(Usuario cliente, Orcamento orcamento, LocalDateTime dataHoraSolicitou,
                              LocalDateTime dataHoraEntregue, String endereco,
                              String contato, StatusSolicitacao statusSolicitacao) {
        this.cliente = cliente;
        this.orcamento = orcamento;
        this.dataHoraSolicitou = dataHoraSolicitou;
        this.dataHoraEntregue = dataHoraEntregue;
        this.endereco = endereco;
        this.contato = contato;
        this.statusSolicitacao = statusSolicitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SolicitacaoProduto that = (SolicitacaoProduto) o;
        return Objects.equals(cliente, that.cliente) && Objects.equals(orcamento, that.orcamento) && Objects.equals(dataHoraSolicitou, that.dataHoraSolicitou) && Objects.equals(dataHoraEntregue, that.dataHoraEntregue) && Objects.equals(endereco, that.endereco) && Objects.equals(contato, that.contato) && statusSolicitacao == that.statusSolicitacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, orcamento, dataHoraSolicitou, dataHoraEntregue, endereco, contato, statusSolicitacao);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHoraSolicitou() {
        return dataHoraSolicitou;
    }

    public void setDataHoraSolicitou(LocalDateTime dataHoraSolicitou) {
        this.dataHoraSolicitou = dataHoraSolicitou;
    }

    public LocalDateTime getDataHoraEntregue() {
        return dataHoraEntregue;
    }

    public void setDataHoraEntregue(LocalDateTime dataHoraEntregue) {
        this.dataHoraEntregue = dataHoraEntregue;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }


}
