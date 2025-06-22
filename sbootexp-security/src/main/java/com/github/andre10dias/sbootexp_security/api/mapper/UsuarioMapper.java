package com.github.andre10dias.sbootexp_security.api.mapper;

import com.github.andre10dias.sbootexp_security.api.dto.UsuarioDTO;
import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario entity) {
        return new UsuarioDTO(entity.getId(), entity.getNome(), entity.getLogin());
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        return new Usuario(dto.id(), dto.nome(), dto.login());
    }

}
