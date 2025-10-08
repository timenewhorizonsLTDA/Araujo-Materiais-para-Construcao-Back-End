package com.materiais.araujo.araujo_materiais_api.model.produto;

import com.materiais.araujo.araujo_materiais_api.model.orcamento.Orcamento;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Integer estoqueMinimo;

    @ManyToOne
    private Orcamento orcamento;

    public Produto() {
    }

    public Produto(String nome, String codigo, Double preco, Integer quantidade, Integer estoqueMinimo, Orcamento orcamento) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
        this.orcamento = orcamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome) && Objects.equals(codigo, produto.codigo) && Objects.equals(preco, produto.preco) && Objects.equals(quantidade, produto.quantidade) && Objects.equals(estoqueMinimo, produto.estoqueMinimo) && Objects.equals(orcamento, produto.orcamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codigo, preco, quantidade, estoqueMinimo, orcamento);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }


}
