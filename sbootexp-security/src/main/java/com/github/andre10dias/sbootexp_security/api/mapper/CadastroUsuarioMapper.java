package com.github.andre10dias.sbootexp_security.api.mapper;

import com.github.andre10dias.sbootexp_security.api.dto.CadastroUsuarioDTO;
import com.github.andre10dias.sbootexp_security.api.dto.UsuarioComSenhaDTO;
import com.github.andre10dias.sbootexp_security.api.dto.UsuarioDTO;
import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import com.github.andre10dias.sbootexp_security.domain.entity.UsuarioGrupo;
import com.github.andre10dias.sbootexp_security.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CadastroUsuarioMapper {

    private final GrupoRepository grupoRepository;

    public static CadastroUsuarioDTO toDTO(Usuario entity) {
        List<String> permissoes = new ArrayList<>();

        UsuarioComSenhaDTO usuarioDTO = UsuarioComSenhaMapper.toDTO(entity);
        entity.getUsuarioGrupos().forEach(
                usuarioGrupo -> permissoes.add(usuarioGrupo.getGrupo().getNome())
        );

        return new CadastroUsuarioDTO(
                usuarioDTO,
                permissoes
        );
    }

}
