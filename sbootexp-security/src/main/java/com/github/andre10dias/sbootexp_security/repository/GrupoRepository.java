package com.github.andre10dias.sbootexp_security.repository;

import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

    List<Grupo> findByNomeIn(List<String> nome);

}
