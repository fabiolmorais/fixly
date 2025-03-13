package com.projetomaisprati.fixly.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private Long chatId;
    private String content;
    private Long senderId;
    private Long receiverId;
    private String chatName;
    private TipoMensagem tipoMensagem;
    private NotificationType type;
    private byte[] media;
}
