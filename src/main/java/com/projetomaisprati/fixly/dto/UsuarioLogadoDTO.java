package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UsuarioLogadoDTO {

    private Long id;
    private String nome;
    private String email;
    private Instant dataCriacao;

    private List<String> roles = new ArrayList<>();

    public UsuarioLogadoDTO(Usuario entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        dataCriacao = entity.getDataCriacao();
        for (GrantedAuthority role : entity.getRoles()) {
            roles.add(role.getAuthority());
        }
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

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public List<String> getRoles() {
        return roles;
    }
}
