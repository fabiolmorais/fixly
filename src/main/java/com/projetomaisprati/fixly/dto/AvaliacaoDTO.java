package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Avaliacao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public class AvaliacaoDTO {
    private Long id;
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    private Integer nota;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter um comentário")
    private String comentario;
    private Instant criadoEm;
    private Instant atualizadoEm;
    private UsuarioMinDTO avaliador;
    private UsuarioMinDTO avaliado;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Long id, Integer nota, String comentario, Instant criadoEm, Instant atualizadoEm, UsuarioMinDTO avaliador, UsuarioMinDTO avaliado) {
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
        avaliador = (entidade.getAvaliador() == null) ? null : new UsuarioMinDTO(entidade.getAvaliador());
        avaliado = (entidade.getAvaliado() == null) ? null : new UsuarioMinDTO(entidade.getAvaliado());
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

    public UsuarioMinDTO getAvaliador() {
        return avaliador;
    }

    public UsuarioMinDTO getAvaliado() {
        return avaliado;
    }
}
