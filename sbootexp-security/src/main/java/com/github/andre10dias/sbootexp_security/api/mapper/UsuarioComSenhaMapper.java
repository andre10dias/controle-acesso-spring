package com.github.andre10dias.sbootexp_security.api.mapper;

import com.github.andre10dias.sbootexp_security.api.dto.UsuarioComSenhaDTO;
import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;

public class UsuarioComSenhaMapper {

    public static UsuarioComSenhaDTO toDTO(Usuario entity) {
        return new UsuarioComSenhaDTO(entity.getId(), entity.getNome(), entity.getLogin(), entity.getSenha());
    }

    public static Usuario toEntity(UsuarioComSenhaDTO dto) {
        return new Usuario(dto.id(), dto.nome(), dto.login(), dto.senha());
    }

}
