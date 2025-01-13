package com.projetomaisprati.fixly.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_avalicao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    @Column(columnDefinition = "TEXT")
    private String comentario;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualizadoEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Integer nota, String comentario, Instant criadoEm, Instant atualizadoEm, Usuario usuario) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
