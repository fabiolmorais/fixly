package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.EstadoMensagem;
import com.projetomaisprati.fixly.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    @Query("SELECT m FROM Mensagem m WHERE m.chat.id = :chatId ORDER BY m.createdDate")
    List<Mensagem> searchMensagemByChatId(@Param("chatId") Long chatId);

    @Modifying
    @Query("UPDATE Mensagem SET estado = :newState WHERE chat.id = :chatId")
    void setMessageToSeenByChatId(@Param("chatId") Long chatId, @Param("newState")EstadoMensagem estado);
}
