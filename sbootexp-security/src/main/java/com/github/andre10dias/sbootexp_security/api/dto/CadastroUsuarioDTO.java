package com.github.andre10dias.sbootexp_security.api.dto;

import java.util.List;

public record CadastroUsuarioDTO(
        UsuarioComSenhaDTO usuario,
        List<String> permissoes
) {
}
