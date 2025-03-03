package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoriaDTO {
    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter o nome da categoria")
    @Size(min = 2, message = "Nome da categoria precisa ter pelo menos 2 caracteres")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
