package com.projetomaisprati.fixly.controllers;

import com.projetomaisprati.fixly.dto.ChatDTO;
import com.projetomaisprati.fixly.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Long> createChat(
            @RequestParam(name = "sender-id") Long senderId,
            @RequestParam(name = "receiver-id") Long receiverId
    ) {
        final Long chatId = chatService.createChat(senderId, receiverId);
        return ResponseEntity.ok(chatId);
    }

    @GetMapping
    public ResponseEntity<List<ChatDTO>> getChatsByReceiver() {
        return ResponseEntity.ok(chatService.getChatsByReceiverId());
    }
}
