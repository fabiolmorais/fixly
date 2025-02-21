package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class UsuarioInsertDTO  extends UsuarioDTO{

    @NotBlank(message = "Campo requerido")
    @Size(min = 8, message = "Senha precisa ter no mínimo 8 caracteres")
    private String senha;

    public UsuarioInsertDTO() {
        super();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha) {
        this.senha = senha;
    }
}
