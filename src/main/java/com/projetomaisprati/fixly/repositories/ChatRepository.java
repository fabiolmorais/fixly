package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Chat;
import com.projetomaisprati.fixly.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByClienteAndPrestador(Usuario cliente, Usuario prestador);
}
