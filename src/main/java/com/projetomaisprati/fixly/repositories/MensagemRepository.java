package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByChatId(Long chatId);
}
