package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.MensagensDTO;
import com.projetomaisprati.fixly.entities.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @Autowired
    private MensagemService mensagemService;

    @MessageMapping("/chat/enviar")
    @SendTo("/chat/public")
    public Mensagem enviarMensagem(Mensagem mensagem) {
        MensagensDTO dto = mensagemService.enviarMensagem(
                mensagem.getChat().getId(),
                mensagem.getRemetente().getId(),
                mensagem.getConteudo(),
                mensagem.getTipo());

        return new Mensagem(dto);
    }
}
