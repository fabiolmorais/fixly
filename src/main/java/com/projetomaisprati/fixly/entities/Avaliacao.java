package com.projetomaisprati.fixly.entities;

import com.projetomaisprati.fixly.services.AvaliacaoService;
import jakarta.persistence.*;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_avaliacao")
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
    @JoinColumn(name = "avaliador_id")
    private Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "avaliado_id")
    private Usuario avaliado;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Integer nota, String comentario, Instant criadoEm, Instant atualizadoEm, Usuario avaliador, Usuario avaliado) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
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

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Usuario getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(Usuario avaliado) {
        this.avaliado = avaliado;
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

    @Transient
    @Autowired
    private transient ApplicationContext applicationContext;

    @PostPersist
    @PostUpdate
    @PostRemove
    private void updateAverageRating() {
        AvaliacaoService avaliacaoService = applicationContext.getBean(AvaliacaoService.class);
        avaliacaoService.updateAverageRating(this.avaliado);
    }
}
