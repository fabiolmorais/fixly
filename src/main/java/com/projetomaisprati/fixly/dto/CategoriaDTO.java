package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDTO {
    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter o nome da categoria")
    @Size(min = 2, message = "Nome da categoria precisa ter pelo menos 2 caracteres")
    private String nome;
    private List<ServicoDTO> servicos = new ArrayList<>();

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        servicos = entidade.getServicos().stream().map(ServicoDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<ServicoDTO> getServicos() {
        return servicos;
    }
}
