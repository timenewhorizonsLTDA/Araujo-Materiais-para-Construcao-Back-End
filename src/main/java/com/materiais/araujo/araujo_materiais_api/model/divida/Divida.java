package com.materiais.araujo.araujo_materiais_api.model.divida;

import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario cliente;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private LocalDateTime dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDivida statusDivida;

    public Divida() {
    }

    public Divida(Usuario cliente, Double valor, LocalDateTime dataVencimento, StatusDivida statusDivida) {
        this.cliente = cliente;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.statusDivida = statusDivida;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Divida divida = (Divida) o;
        return Objects.equals(cliente, divida.cliente) && Objects.equals(valor, divida.valor) && Objects.equals(dataVencimento, divida.dataVencimento) && statusDivida == divida.statusDivida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, valor, dataVencimento, statusDivida);
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public StatusDivida getStatusDivida() {
        return statusDivida;
    }

    public void setStatusDivida(StatusDivida statusDivida) {
        this.statusDivida = statusDivida;
    }
}
