package com.projetomaisprati.fixly.repositories;

import com.projetomaisprati.fixly.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT obj FROM Role obj WHERE obj.authority LIKE %:authority%")
    Role searchByAuthority(String authority);
}
