package com.projetomaisprati.fixly.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Solicitacao> solicitacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Servico> servicos = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }

    public Set<Servico> getServicos() {
        return servicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
