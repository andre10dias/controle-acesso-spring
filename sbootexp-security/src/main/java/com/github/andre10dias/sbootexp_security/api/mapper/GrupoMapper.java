package com.github.andre10dias.sbootexp_security.api.mapper;

import com.github.andre10dias.sbootexp_security.api.dto.GrupoDTO;
import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;

public class GrupoMapper {

    public static GrupoDTO toDTO(Grupo entity) {
        return new GrupoDTO(entity.getId(), entity.getNome());
    }

    public static Grupo toEntity(GrupoDTO dto) {
        return new Grupo(dto.id(), dto.nome());
    }

}
