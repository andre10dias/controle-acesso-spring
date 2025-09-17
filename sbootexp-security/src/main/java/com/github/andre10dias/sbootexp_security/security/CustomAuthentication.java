package com.github.andre10dias.sbootexp_security.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

public class CustomAuthentication implements Authentication, Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    private final IdentificacaoUsuario identificacaoUsuario;

    public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) {
        if (identificacaoUsuario == null) {
            throw new ExceptionInInitializerError("IdentificacaoUsuario cannot be null");
        }

        this.identificacaoUsuario = identificacaoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario.getPermissoes()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        /*
         * This method is intentionally left empty and throws UnsupportedOperationException because
         * the authentication state should only be set during the authentication process.
         * Once created, the authentication token's state should remain immutable.
         */
        throw new UnsupportedOperationException("Cannot set the authentication state - use the appropriate AuthenticationManager to authenticate");
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getNome();
    }
}
