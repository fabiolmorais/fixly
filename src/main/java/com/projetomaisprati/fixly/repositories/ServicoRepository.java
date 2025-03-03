package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.prestador.id = :prestadorId")
    Page<Servico> buscarServicosPorPrestador(@Param("prestadorId") Long prestadorId, Pageable pageable);
}
