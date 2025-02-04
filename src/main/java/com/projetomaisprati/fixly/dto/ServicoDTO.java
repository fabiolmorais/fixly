package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ServicoDTO {
    private Long id;

    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter o nome do serviço")
    private String nome;

    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "Nome do complemento precisa ter pelo menos 2 caracteres")
    private String descricao;

    private Double precoMinimo;

    private Double precoMaximo;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private CategoriaDTO categoria;

    public ServicoDTO() {
    }

    public ServicoDTO(Long id, String nome, String descricao, Double precoMinimo, Double precoMaximo, CategoriaDTO categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
        this.categoria = categoria;
    }

    public ServicoDTO(Servico entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        descricao = entidade.getDescricao();
        precoMinimo = entidade.getPrecoMinimo();
        precoMaximo = entidade.getPrecoMaximo();
        categoria = (entidade.getCategoria() == null) ? null : new CategoriaDTO(entidade.getCategoria());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoMinimo() {
        return precoMinimo;
    }

    public Double getPrecoMaximo() {
        return precoMaximo;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }
}
