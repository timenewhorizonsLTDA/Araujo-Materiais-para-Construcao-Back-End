package com.materiais.araujo.araujo_materiais_api.model.usuario;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class CodigoAutorizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    private String codigo;

    private Instant horarioDeEnvio;

    private Instant horarioDeExpiracao;

    public CodigoAutorizacao() {
    }

    public CodigoAutorizacao(Usuario usuario, String uuid, Instant horarioDeEnvio, Instant horarioDeExpiracao) {
        this.usuario = usuario;
        this.codigo = uuid;
        this.horarioDeEnvio = horarioDeEnvio;
        this.horarioDeExpiracao = horarioDeExpiracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Instant getHorarioDeEnvio() {
        return horarioDeEnvio;
    }

    public void setHorarioDeEnvio(Instant horarioDeEnvio) {
        this.horarioDeEnvio = horarioDeEnvio;
    }

    public Instant getHorarioDeExpiracao() {
        return horarioDeExpiracao;
    }

    public void setHorarioDeExpiracao(Instant horarioDeExpiracao) {
        this.horarioDeExpiracao = horarioDeExpiracao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
