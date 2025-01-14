package com.projetomaisprati.fixly.entities;

import jakarta.persistence.*;

import java.time.Instant;
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
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataCriacao;

    @OneToMany(mappedBy = "usuario")
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany(mappedBy = "prestador")
    private Set<Servico> servicos = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private List<Orcamento> orcamentosCliente = new ArrayList<>();

    @OneToMany(mappedBy = "prestador")
    private List<Orcamento> orcamentosPrestador = new ArrayList<>();

    @OneToMany(mappedBy = "avaliador")
    private List<Avaliacao> avaliacoesAvaliador = new ArrayList<>();

    @OneToMany(mappedBy = "avaliado")
    private List<Avaliacao> avaliacoesAvaliado = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Chat> chatsCliente = new ArrayList<>();

    @OneToMany(mappedBy = "prestador")
    private List<Chat> chatsPrestador = new ArrayList<>();

    @OneToMany(mappedBy = "remetente")
    private List<Mensagem> mensagems = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, TipoUsuario tipo, Instant dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
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

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Orcamento> getOrcamentosCliente() {
        return orcamentosCliente;
    }

    public List<Orcamento> getOrcamentosPrestador() {
        return orcamentosPrestador;
    }

    public List<Avaliacao> getAvaliacoesAvaliador() {
        return avaliacoesAvaliador;
    }

    public List<Avaliacao> getAvaliacoesAvaliado() {
        return avaliacoesAvaliado;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public Set<Servico> getServicos() {
        return servicos;
    }

    public List<Chat> getChatsCliente() {
        return chatsCliente;
    }

    public List<Chat> getChatsPrestador() {
        return chatsPrestador;
    }

    public List<Mensagem> getMensagems() {
        return mensagems;
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
