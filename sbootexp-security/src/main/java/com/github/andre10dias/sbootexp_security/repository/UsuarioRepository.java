package com.github.andre10dias.sbootexp_security.repository;

import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
