package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Servico;
import com.projetomaisprati.fixly.entities.Usuario;
import com.projetomaisprati.fixly.projections.UserDetailsProjection;
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
            "WHERE u.tipo = 1 " +
            "AND UPPER(e.cidade) LIKE UPPER(CONCAT('%', :cidade, '%'))")
    Page<Usuario> buscarUsuariosPorEndereco(@Param("cidade") String cidade, Pageable pageable);

    @Query("SELECT u FROM Usuario u " +
            "WHERE u.tipo = 1 " +
            "AND u.id = :id")
    Optional<Usuario> buscarUsuarioPorId(Long id);

    @Query(nativeQuery = true, value = """
				SELECT tb_usuario.email AS username, tb_usuario.senha AS password, tb_role.id AS roleId, tb_role.authority
				FROM tb_usuario
				INNER JOIN tb_user_role ON tb_usuario.id = tb_user_role.user_id
				INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
				WHERE tb_usuario.email = :email
			""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

    Optional<Usuario> findByEmail (String email);
}
