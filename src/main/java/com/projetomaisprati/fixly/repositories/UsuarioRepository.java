package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u " +
            "JOIN u.enderecos e " +
            "WHERE u.tipo = 1 " +
            "AND UPPER(e.cidade) LIKE UPPER(CONCAT('%', :cidade, '%'))")
    Page<Usuario> buscarUsuariosPorEndereco(@Param("cidade") String cidade, Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE u.tipo = 1 " +
            "AND u.id = :id")
    Optional<Usuario> buscarUsuarioPorId(Long id);
}
