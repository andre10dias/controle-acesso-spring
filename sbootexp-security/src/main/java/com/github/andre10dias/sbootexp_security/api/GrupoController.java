package com.github.andre10dias.sbootexp_security.api;

import com.github.andre10dias.sbootexp_security.api.dto.GrupoDTO;
import com.github.andre10dias.sbootexp_security.api.mapper.GrupoMapper;
import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import com.github.andre10dias.sbootexp_security.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController implements GenericController {

    private final GrupoService grupoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<Void> save(@RequestBody GrupoDTO dto) {
        Grupo grupo = GrupoMapper.toEntity(dto);
        grupoService.salvar(grupo);
        URI location = gerarHeaderLocation(grupo.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GrupoDTO>> listar() {
        List<Grupo> grupos = grupoService.listar();
        List<GrupoDTO> dtos = grupos.stream().map(GrupoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

}
