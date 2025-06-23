package com.github.andre10dias.sbootexp_security.api;

import com.github.andre10dias.sbootexp_security.api.dto.CadastroUsuarioDTO;
import com.github.andre10dias.sbootexp_security.api.dto.UsuarioDTO;
import com.github.andre10dias.sbootexp_security.api.mapper.CadastroUsuarioMapper;
import com.github.andre10dias.sbootexp_security.api.mapper.UsuarioMapper;
import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import com.github.andre10dias.sbootexp_security.domain.entity.UsuarioGrupo;
import com.github.andre10dias.sbootexp_security.service.GrupoService;
import com.github.andre10dias.sbootexp_security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements GenericController {

    private final UsuarioService usuarioService;
    private final GrupoService grupoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<Void> save(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioService.salvar(dto);
        URI location = gerarHeaderLocation(usuario.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CadastroUsuarioDTO>> listar() {
        List<Usuario> usuarios = usuarioService.listar();
        List<CadastroUsuarioDTO> dtos = usuarios.stream().map(CadastroUsuarioMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }



}
