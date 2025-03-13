package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.entities.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(Long usuarioId, Notification notification) {
        log.info("Sending WS notification to {} with payload {}", usuarioId, notification);
        messagingTemplate.convertAndSendToUser(Long.toString(usuarioId), "/chat", notification);
    }
}
