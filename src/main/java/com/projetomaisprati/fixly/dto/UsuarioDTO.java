package com.projetomaisprati.fixly.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu nome")
    private String nome;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa digitar seu email")
    @Email(message = "Email inválido")
    private String email;
    private String cpfOuCnpj;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;
    private TipoUsuario tipo;
    private Instant dataCriacao;

    Set<RoleDTO> roles = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String cpfOuCnpj, LocalDate nascimento, TipoUsuario tipo, Instant dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.nascimento = nascimento;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public UsuarioDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();
        cpfOuCnpj = entidade.getCpfOuCnpj();
        nascimento = entidade.getNascimento();
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public LocalDate getNascimento() {
        return nascimento;
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
