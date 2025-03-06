package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Chat;
import com.projetomaisprati.fixly.entities.Mensagem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChatDTO {
    private Long id;
    private Instant dataCriacao;
    private UsuarioMinDTO cliente;
    private UsuarioMinDTO prestador;
    private List<MensagensDTO> mensagensDTO = new ArrayList<>();

    public ChatDTO() {
    }

    public ChatDTO(Long id, Instant dataCriacao, UsuarioMinDTO cliente, UsuarioMinDTO prestador, List<MensagensDTO> mensagensDTO) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.prestador = prestador;
        this.mensagensDTO = mensagensDTO;
    }

    public ChatDTO(Chat entidade) {
        id = entidade.getId();
        dataCriacao = entidade.getDataCriacao();
        cliente = (entidade.getCliente() == null) ? null : new UsuarioMinDTO(entidade.getCliente());
        prestador = (entidade.getPrestador() == null) ? null : new UsuarioMinDTO(entidade.getPrestador());
        entidade.getMensagems().forEach((mensagem -> this.mensagensDTO.add(new MensagensDTO(mensagem))));
    }

    public Long getId() {
        return id;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public UsuarioMinDTO getCliente() {
        return cliente;
    }

    public UsuarioMinDTO getPrestador() {
        return prestador;
    }

    public List<MensagensDTO> getMensagensDTO() {
        return mensagensDTO;
    }
}
