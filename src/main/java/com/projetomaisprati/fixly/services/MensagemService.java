package com.projetomaisprati.fixly.services;

import com.projetomaisprati.fixly.dto.MensagemRequestDTO;
import com.projetomaisprati.fixly.entities.*;
import com.projetomaisprati.fixly.mapper.MensagemMapper;
import com.projetomaisprati.fixly.repositories.ChatRepository;
import com.projetomaisprati.fixly.repositories.MensagemRepository;
import com.projetomaisprati.fixly.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final ChatRepository chatRepository;
    private final MensagemMapper mapper;
    private final NotificationService notificationService;
    private final FileService fileService;
    private final UsuarioAutenticado usuarioAutenticado;

    public void saveMessage(MensagemRequestDTO mensagemRequestDTO) {
        Chat chat = chatRepository.findById(mensagemRequestDTO.getChatId())
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));

        Mensagem mensagem = new Mensagem();
        mensagem.setConteudo(mensagemRequestDTO.getConteudo());
        mensagem.setChat(chat);
        mensagem.setSenderId(mensagemRequestDTO.getSenderId());
        mensagem.setReceiverId(mensagemRequestDTO.getReceiverId());
        mensagem.setTipo(mensagemRequestDTO.getTipo());
        mensagem.setEstado(EstadoMensagem.SENT);

        mensagemRepository.save(mensagem);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .tipoMensagem(mensagemRequestDTO.getTipo())
                .content(mensagemRequestDTO.getConteudo())
                .senderId(mensagemRequestDTO.getSenderId())
                .receiverId(mensagemRequestDTO.getReceiverId())
                .type(NotificationType.MESSAGE)
                .chatName(chat.getTargetChatName(mensagem.getSenderId()))
                .build();

        notificationService.sendNotification(mensagemRequestDTO.getReceiverId(), notification);
    }

    @Transactional
    public void setMessagesToSeen(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
        final Long recipientId = getRecipientId(chat);

        mensagemRepository.setMessageToSeenByChatId(chatId, EstadoMensagem.SEEN);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .type(NotificationType.SEEN)
                .receiverId(recipientId)
                .senderId(getSenderId(chat))
                .build();

        notificationService.sendNotification(recipientId, notification);
    }

    public void uploadMediaMessage(Long chatId, MultipartFile file) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
        final Long senderId = getSenderId(chat);
        final Long receiverId = getRecipientId(chat);

        final String filePath = fileService.saveFile(file, senderId);
        Mensagem mensagem = new Mensagem();
        mensagem.setReceiverId(receiverId);
        mensagem.setSenderId(senderId);
        mensagem.setEstado(EstadoMensagem.SENT);
        mensagem.setTipo(TipoMensagem.IMAGE);
        mensagem.setMediaFilePath(filePath);
        mensagem.setChat(chat);
        mensagemRepository.save(mensagem);

        Notification notification = Notification.builder()
                .chatId(chat.getId())
                .type(NotificationType.IMAGE)
                .senderId(senderId)
                .receiverId(receiverId)
                .tipoMensagem(TipoMensagem.IMAGE)
                .media(FileUtils.readFileFromLocation(filePath))
                .build();
        notificationService.sendNotification(receiverId, notification);
    }

    private Long getSenderId(Chat chat) {
        Usuario currentUsuario = usuarioAutenticado.autenticated();
        if (chat.getSender().getId().equals(currentUsuario.getId())) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private Long getRecipientId(Chat chat) {
        Usuario currentUsuario = usuarioAutenticado.autenticated();
        if (chat.getSender().getId().equals(currentUsuario.getId())) {
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }
}
