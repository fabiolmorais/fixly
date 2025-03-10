package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Chat;
import com.projetomaisprati.fixly.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT DISTINCT c FROM Chat c " +
            "WHERE c.sender.id = :senderId OR c.recipient.id = :senderId " +
            "ORDER BY createdDate DESC")
    Optional<Chat> searchChatBySenderId(@Param("senderId") Long senderId);

    @Query("SELECT DISTINCT c FROM Chat c " +
            "WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) " +
            "OR (c.sender.id = :recipientId AND c.recipient.id = :senderId) " +
            "ORDER BY createdDate DESC")
    Optional<Chat> searchChatBySenderIdAndReceiver(@Param("senderId") Long id, @Param("recipientId") Long recipientId);
}
