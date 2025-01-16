package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Usuario;

public class EnderecoUsuarioDTO {

    private Long id;
    private String nome;

    public EnderecoUsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public EnderecoUsuarioDTO(Usuario entidade) {
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
