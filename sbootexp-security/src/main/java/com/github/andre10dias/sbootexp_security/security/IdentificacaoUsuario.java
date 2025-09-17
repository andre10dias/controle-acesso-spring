package com.github.andre10dias.sbootexp_security.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdentificacaoUsuario implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String nome;
    private String login;
    private List<String> permissoes;

    public List<String> getPermissoes() {
        if (permissoes == null) {
            permissoes = new ArrayList<>();
        }
        return permissoes;
    }

}
