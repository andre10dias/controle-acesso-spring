package com.github.andre10dias.sbootexp_security.service;

import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import com.github.andre10dias.sbootexp_security.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GrupoService {

    private final GrupoRepository repository;

    public void salvar(Grupo grupo) {
        repository.save(grupo);
    }

    public List<Grupo> listar() {
        return repository.findAll();
    }

}
