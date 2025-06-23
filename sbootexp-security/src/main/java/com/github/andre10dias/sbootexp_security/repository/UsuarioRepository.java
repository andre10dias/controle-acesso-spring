package com.github.andre10dias.sbootexp_security.repository;

import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByLogin(String login);
}
