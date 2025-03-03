package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class PrestadorDetalhadoDTO {
    private Long id;
    private String nome;
    private String email;
    private String tipo;
    private List<ServicoDTO> servicos;
    private List<AvaliacaoDTO> avaliacoes;

    public PrestadorDetalhadoDTO() {
    }

    public PrestadorDetalhadoDTO(Long id, String nome, String email, String tipo, String descricao, List<ServicoDTO> servicos, List<AvaliacaoDTO> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.servicos = servicos;
        this.avaliacoes = avaliacoes;
    }

    public PrestadorDetalhadoDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();
        tipo = entidade.getTipo().toString();
        servicos = entidade.getServicos().stream().map(ServicoDTO::new).collect(Collectors.toList());
        avaliacoes = entidade.getAvaliacoesAvaliado().stream().map(AvaliacaoDTO::new).collect(Collectors.toList());
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

    public String getTipo() {
        return tipo;
    }

    public List<ServicoDTO> getServicos() {
        return servicos;
    }

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacoes;
    }
}
