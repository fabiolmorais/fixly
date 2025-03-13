package com.projetomaisprati.fixly.mapper;

import com.projetomaisprati.fixly.dto.ChatDTO;
import com.projetomaisprati.fixly.entities.Chat;
import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatDTO toChatDTO(Chat chat, Long id) {
        return ChatDTO.builder()
                .id(chat.getId())
                .nome(chat.getChatName(id))
                .unreadCount(chat.getUnreadMessages(id))
                .lastMessage(chat.getLastMessage())
                .lastMessageTime(chat.getLastMessageTime())
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .build();
    }
}
