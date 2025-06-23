package com.github.andre10dias.sbootexp_security.config;

import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import com.github.andre10dias.sbootexp_security.security.CustomAuthentication;
import com.github.andre10dias.sbootexp_security.security.IdentificacaoUsuario;
import com.github.andre10dias.sbootexp_security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.obterUsuarioComPermissoes(login);
        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            IdentificacaoUsuario identificacao = new IdentificacaoUsuario(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getLogin(),
                    usuario.getPermissoes()
            );

            return new CustomAuthentication(identificacao);
        }
        
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
