package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Orcamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    @Query("SELECT o FROM Orcamento o WHERE o.prestador.id = :prestadorId")
    Page<Orcamento> buscarOrcamentoPorPrestador(@Param("prestadorId") Long prestadorId, Pageable pageable);

    @Query("SELECT o FROM Orcamento o WHERE o.cliente.id = :clienteId")
    Page<Orcamento> buscarOrcamentoPorCliente(@Param("clienteId") Long clienteId, Pageable pageable);
}
