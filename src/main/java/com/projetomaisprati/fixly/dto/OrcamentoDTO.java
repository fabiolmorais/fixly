package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Orcamento;
import com.projetomaisprati.fixly.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public class OrcamentoDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "Nome do complemento precisa ter pelo menos 2 caracteres")
    private String descricao;
    @Positive(message = "O preço deve ser positivo")
    private Double preco;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Instant dataCriacao;
    private Status status;
    private UsuarioMinDTO cliente;
    private UsuarioMinDTO prestador;
    private EnderecoDTO endereco;

    public OrcamentoDTO() {
    }

    public OrcamentoDTO(Long id, String descricao, Double preco, LocalDate dataInicio, LocalDate dataFim, Instant dataCriacao, Status status, UsuarioMinDTO cliente, UsuarioMinDTO prestador, EnderecoDTO endereco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.cliente = cliente;
        this.prestador = prestador;
        this.endereco = endereco;
    }

    public OrcamentoDTO(Orcamento entidade) {
        id = entidade.getId();
        descricao = entidade.getDescricao();
        preco = entidade.getPreco();
        dataInicio = entidade.getDataInicio();
        dataFim = entidade.getDataFim();
        dataCriacao = entidade.getDataCriacao();
        status = entidade.getStatus();
        cliente = (entidade.getCliente() == null) ? null : new UsuarioMinDTO(entidade.getCliente());
        prestador = (entidade.getPrestador() == null) ? null : new UsuarioMinDTO(entidade.getPrestador());
        endereco = (entidade.getEndereco() == null) ? null : new EnderecoDTO(entidade.getEndereco());
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public UsuarioMinDTO getCliente() {
        return cliente;
    }

    public UsuarioMinDTO getPrestador() {
        return prestador;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }
}
