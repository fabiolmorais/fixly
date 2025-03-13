package com.projetomaisprati.fixly.mapper;

import com.projetomaisprati.fixly.dto.MessageResponseDTO;
import com.projetomaisprati.fixly.entities.Mensagem;
import com.projetomaisprati.fixly.entities.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class MensagemMapper {
    public MessageResponseDTO toMessageResponse(Mensagem mensagem) {
        return MessageResponseDTO.builder()
                .id(mensagem.getId())
                .conteudo(mensagem.getConteudo())
                .senderId(mensagem.getSenderId())
                .receiverId(mensagem.getReceiverId())
                .tipo(mensagem.getTipo())
                .estado(mensagem.getEstado())
                .createdAt(mensagem.getCreatedDate())
                .media(FileUtils.readFileFromLocation(mensagem.getMediaFilePath()))
                .build();
    }
}
