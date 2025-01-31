package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.TipoUsuario;
import com.projetomaisprati.fixly.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query("SELECT u FROM Usuario u " +
            "JOIN u.enderecos e " +
            "LEFT JOIN u.servicos s " +
            "WHERE e.cidade = :cidade AND u.tipo = :tipoUsuario " +
            "AND (:categoriaId IS NULL OR s.categoria.id = :categoriaId) " +
            "AND (:precoMin IS NULL OR s.precoMinimo >= :precoMin) " +
            "AND (:precoMax IS NULL OR s.precoMaximo <= :precoMax) " +
            "AND (:avaliacao IS NULL OR u.avaliacaoMedia >= :avaliacao)")
    Page<Usuario> findPrestadoresByCidadeAndFilters(
            @Param("tipoUsuario") TipoUsuario tipoUsuario,
            @Param("cidade") String cidade,
            @Param("categoriaId") Long categoriaId,
            @Param("precoMin") Double precoMin,
            @Param("precoMax") Double precoMax,
            @Param("avaliacao") Double avaliacao,
            Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE u.tipo = :tipoUsuario ")
    Optional<Usuario> findPrestadorById(@Param("id") Long id, TipoUsuario tipoUsuario);

}
