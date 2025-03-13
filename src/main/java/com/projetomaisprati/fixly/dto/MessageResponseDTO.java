package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.EstadoMensagem;
import com.projetomaisprati.fixly.entities.TipoMensagem;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponseDTO {

    private Long id;
    private String conteudo;
    private EstadoMensagem estado;
    private TipoMensagem tipo;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime createdAt;
    private byte[] media;
}
