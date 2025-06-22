package com.github.andre10dias.sbootexp_security.api.dto;

import java.util.UUID;

public record UsuarioDTO(
        UUID id,
        String nome,
        String login
) {
}
