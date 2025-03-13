package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.ChatDTO;
import com.projetomaisprati.fixly.entities.Chat;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.mapper.ChatMapper;
import com.projetomaisprati.fixly.repositories.ChatRepository;
import com.projetomaisprati.fixly.repositories.UsuarioRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final ChatMapper mapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<ChatDTO> getChatsByReceiverId() {
        Usuario currentUser = usuarioAutenticado.autenticated();
        final Long userId = currentUser.getId();
        return chatRepository.searchChatBySenderId(userId)
                .stream()
                .map(chat -> mapper.toChatDTO(chat, userId))
                .toList();
    }

    public Long createChat(Long senderId, Long receiverId) {

        Optional<Chat> existingChat = chatRepository.searchChatBySenderIdAndReceiver(senderId, receiverId);
        if (existingChat.isPresent()) {
            return existingChat.get().getId();
        }

        Usuario sender = usuarioRepository.findById(senderId)
                .orElseThrow(() ->  new ResourceNotFoundException("User with id " + senderId + " not found"));
        Usuario receiver = usuarioRepository.findById(receiverId)
                .orElseThrow(() ->  new ResourceNotFoundException("User with id " + receiverId + " not found"));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
