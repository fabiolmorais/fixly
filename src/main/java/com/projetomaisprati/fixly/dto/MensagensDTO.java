package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.Mensagem;
import com.projetomaisprati.fixly.entities.TipoMensagem;

import java.time.Instant;

public class MensagensDTO {
    private Long id;
    private String conteudo;
    private Instant dataEnvio;
    private Boolean lida;
    private TipoMensagem tipo;
    private UsuarioMinDTO remetente;
    private ChatDTO chat;

    public MensagensDTO() {
    }

    public MensagensDTO(Long id, String conteudo, Instant dataEnvio, Boolean lida, TipoMensagem tipo, UsuarioMinDTO remetente, ChatDTO chat) {
        this.id = id;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
        this.tipo = tipo;
        this.remetente = remetente;
        this.chat = chat;
    }

    public MensagensDTO(Mensagem entidade) {
        id = entidade.getId();
        conteudo = entidade.getConteudo();
        dataEnvio = entidade.getDataEnvio();
        lida = entidade.getLida();
        tipo = entidade.getTipo();
        remetente = (entidade.getRemetente() == null) ? null : new UsuarioMinDTO(entidade.getRemetente());
        chat = (entidade.getChat() == null) ? null : new ChatDTO(entidade.getChat());
    }

    public Long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Instant getDataEnvio() {
        return dataEnvio;
    }

    public Boolean getLida() {
        return lida;
    }

    public TipoMensagem getTipo() {
        return tipo;
    }

    public UsuarioMinDTO getRemetente() {
        return remetente;
    }

    public ChatDTO getChat() {
        return chat;
    }
}

