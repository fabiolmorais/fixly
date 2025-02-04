package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Avaliacao;
import com.projetomaisprati.fixly.entities.Usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrestadorDTO {

    private Long id;
    private String nome;
    private String email;
    private String tipo;
    private Double avaliacaoMedia;
    private Set<ServicoDTO> servicos;

    public PrestadorDTO() {
    }

    public PrestadorDTO(Long id, String nome, String email, String tipo, Double avaliacaoMedia, Set<ServicoDTO> servicos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.avaliacaoMedia = avaliacaoMedia;
        this.servicos = servicos;
    }

    public PrestadorDTO(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();
        tipo = entidade.getTipo().toString();
        avaliacaoMedia = calcularAvaliacaoMedia(entidade.getAvaliacoesAvaliado());
        servicos = entidade.getServicos().stream().map(ServicoDTO::new).collect(Collectors.toSet());
    }

    private Double calcularAvaliacaoMedia(List<Avaliacao> avaliacoes) {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }
        return avaliacoes.stream().mapToInt(Avaliacao::getNota).average().orElse(0.0);
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

    public Double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public Set<ServicoDTO> getServicos() {
        return servicos;
    }
}
