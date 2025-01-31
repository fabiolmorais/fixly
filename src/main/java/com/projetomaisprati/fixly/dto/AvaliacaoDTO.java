package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Avaliacao;

import java.time.Instant;

public class AvaliacaoDTO {
    private Long id;
    private Integer nota;
    private String comentario;
    private Instant criadoEm;
    private Instant atualizadoEm;
    private UsuarioDTO avaliador;
    private UsuarioDTO avaliado;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Long id, Integer nota, String comentario, Instant criadoEm, Instant atualizadoEm, UsuarioDTO avaliador, UsuarioDTO avaliado) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
    }

    public AvaliacaoDTO(Avaliacao entidade) {
        id = entidade.getId();
        nota = entidade.getNota();
        comentario = entidade.getComentario();
        criadoEm = entidade.getCriadoEm();
        atualizadoEm = entidade.getAtualizadoEm();
        avaliador = (entidade.getAvaliador() == null) ? null : new UsuarioDTO(entidade.getAvaliador());
        avaliado = (entidade.getAvaliado() == null) ? null : new UsuarioDTO(entidade.getAvaliado());
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public UsuarioDTO getAvaliador() {
        return avaliador;
    }

    public UsuarioDTO getAvaliado() {
        return avaliado;
    }
}
