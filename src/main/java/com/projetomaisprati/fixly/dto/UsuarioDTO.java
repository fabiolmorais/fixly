package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu nome")
    private String nome;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu email")
    private String email;
    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Senha precisa ter no mínimo 8 caracteres")
    private String senha;
    private TipoUsuario tipo;
    private Instant dataCriacao;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String senha, TipoUsuario tipo, Instant dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public UsuarioDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();
        senha = entidade.getSenha();
        tipo = entidade.getTipo();
        dataCriacao = entidade.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }
}
