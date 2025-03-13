package com.projetomaisprati.fixly.dto;

import com.projetomaisprati.fixly.entities.TipoMensagem;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensagemRequestDTO {

    private String conteudo;
    private Long senderId;
    private Long receiverId;
    private TipoMensagem tipo;
    private Long chatId;
}
