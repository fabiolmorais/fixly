package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Optional<Avaliacao> findByIdAndAvaliadoId(Long id, Long usuarioId);

    Optional<Avaliacao> findByIdAndAvaliadorId(Long id, Long usuarioId);

    Page<Avaliacao> findByAvaliadoId(Long usuarioId, Pageable pageable);

    Page<Avaliacao> findByAvaliadorId(Long usuarioId, Pageable pageable);
}
