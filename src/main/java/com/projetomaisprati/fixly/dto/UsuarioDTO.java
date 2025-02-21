package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu nome")
    private String nome;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu email")
    private String email;
    private TipoUsuario tipo;
    private Instant dataCriacao;

    Set<RoleDTO> roles = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, TipoUsuario tipo, Instant dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public UsuarioDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();
        tipo = entidade.getTipo();
        dataCriacao = entidade.getDataCriacao();
        entidade.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
