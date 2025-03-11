package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EnderecoDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter o nome da rua")
    private String logradouro;
    @NotBlank(message = "Campo requerido")
    @NotEmpty(message = "Precisa ter o número da casa")
    private String numero;
    @Size(min = 2, message = "Nome do complemento precisa ter pelo menos 2 caracteres")
    private String complemento;
    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "Nome do bairro precisa ter pelo menos 2 caracteres")
    private String bairro;
    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "Nome da cidade precisa ter pelo menos 2 caracteres")
    private String cidade;
    @NotBlank(message = "Campo requerido")
    @Size(min = 2, max = 2, message = "Estado deve ter apenas sua sigla de 2 caracteres, exemplo: SP, GO, BA, PE")
    private String estado;
    @NotBlank(message = "Campo requerido")
    private String cep;
    private Boolean principal;

    private EnderecoUsuarioDTO usuario;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep, EnderecoUsuarioDTO usuario, Boolean principal) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.usuario = usuario;
        this.principal = principal;
    }

    public EnderecoDTO(Endereco entidade) {
        id = entidade.getId();
        logradouro = entidade.getLogradouro();
        numero = entidade.getNumero();
        complemento = entidade.getComplemento();
        bairro = entidade.getBairro();
        cidade = entidade.getCidade();
        estado = entidade.getEstado();
        cep = entidade.getCep();
        usuario = (entidade.getUsuario() == null) ? null : new EnderecoUsuarioDTO(entidade.getUsuario());
        principal = entidade.getPrincipal();
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public EnderecoUsuarioDTO getUsuario() {
        return usuario;
    }

    public Boolean getPrincipal() {
        return principal;
    }
}
