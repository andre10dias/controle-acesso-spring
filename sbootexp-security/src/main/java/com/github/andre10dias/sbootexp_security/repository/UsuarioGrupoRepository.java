package com.github.andre10dias.sbootexp_security.repository;

import com.github.andre10dias.sbootexp_security.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, UUID> {

    @Query(
            """
                SELECT ug.grupo.nome
                        FROM UsuarioGrupo ug
                        join ug.grupo g
                        join ug.usuario u
                        WHERE ug.usuario.id = ?1
            """
    )
    List<String> findPermissoesByUsuarioId(UUID id);

}
