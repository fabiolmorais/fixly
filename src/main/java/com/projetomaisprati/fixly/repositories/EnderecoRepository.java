package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByUsuarioId(Long usuarioId, Pageable pageable);

    Optional<Endereco> findByIdAndUsuarioId(Long id, Long usuarioId);

    @Modifying
    @Query("UPDATE Endereco e SET e.principal = false WHERE e.usuario.id = :usuarioId AND e.principal = true")
    void desmarcarEnderecoPrincipalPorUsuario(@Param("usuarioId") Long usuarioId);


}
