package com.materiais.araujo.araujo_materiais_api.model.orcamento;

import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario cliente;

    @OneToMany(mappedBy = "orcamento")
    private List<Produto> produtos;

    @Column(nullable = false)
    private LocalDateTime dataEmissao;

    @Column(nullable = false)
    private Double valorFinal;


    public Orcamento() {
    }


    public Orcamento(Usuario cliente, List<Produto> produtos, LocalDateTime dataEmissao, Double valorFinal) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataEmissao = dataEmissao;
        this.valorFinal = valorFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return Objects.equals(cliente, orcamento.cliente) && Objects.equals(produtos, orcamento.produtos) && Objects.equals(dataEmissao, orcamento.dataEmissao) && Objects.equals(valorFinal, orcamento.valorFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, produtos, dataEmissao, valorFinal);
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


    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
