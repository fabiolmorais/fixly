package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class UsuarioMinDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu nome")
    private String nome;
    private TipoUsuario tipo;

    public UsuarioMinDTO() {
    }

    public UsuarioMinDTO(Long id, String nome, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public UsuarioMinDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        tipo = entidade.getTipo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }
}
