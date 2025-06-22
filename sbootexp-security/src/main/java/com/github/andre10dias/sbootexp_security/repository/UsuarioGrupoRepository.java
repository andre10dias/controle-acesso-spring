package com.github.andre10dias.sbootexp_security.repository;

import com.github.andre10dias.sbootexp_security.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, UUID> {
}
