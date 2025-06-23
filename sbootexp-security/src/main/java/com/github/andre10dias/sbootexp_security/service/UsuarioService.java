package com.github.andre10dias.sbootexp_security.service;

import com.github.andre10dias.sbootexp_security.api.dto.CadastroUsuarioDTO;
import com.github.andre10dias.sbootexp_security.api.mapper.UsuarioComSenhaMapper;
import com.github.andre10dias.sbootexp_security.api.mapper.UsuarioMapper;
import com.github.andre10dias.sbootexp_security.domain.entity.Grupo;
import com.github.andre10dias.sbootexp_security.domain.entity.Usuario;
import com.github.andre10dias.sbootexp_security.domain.entity.UsuarioGrupo;
import com.github.andre10dias.sbootexp_security.repository.UsuarioGrupoRepository;
import com.github.andre10dias.sbootexp_security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final GrupoService grupoService;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(CadastroUsuarioDTO dto) {
        Usuario usuario = UsuarioComSenhaMapper.toEntity(dto.usuario());
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario = usuarioRepository.save(usuario);

        List<Grupo> grupos = grupoService.buscarPorNomes(dto.permissoes());
        validarNomesDeGruposExistentes(dto.permissoes(), grupos);

        Usuario finalUsuario = usuario;
        List<UsuarioGrupo> usuarioGrupos = grupos.stream()
                .map(grupo -> new UsuarioGrupo(finalUsuario, grupo))
                .collect(Collectors.toList());

        usuario.setUsuarioGrupos(usuarioGrupos);

        usuarioGrupoRepository.saveAll(usuarioGrupos);
        return usuario;
    }

    public Usuario obterUsuarioComPermissoes(String login) {
        Optional<Usuario> optional = Optional.ofNullable(usuarioRepository.findByLogin(login));
        if (optional.isPresent()) {
            return null;
        }

        Usuario usuario = optional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuarioId(usuario.getId());
        usuario.setPermissoes(permissoes);
        return usuario;
    }

    public void validarNomesDeGruposExistentes(List<String> nomesRequisitados, List<Grupo> gruposEncontrados) {
        Set<String> encontrados = gruposEncontrados.stream()
                .map(grupo -> grupo.getNome().toUpperCase())
                .collect(Collectors.toSet());

        List<String> naoEncontrados = nomesRequisitados.stream()
                .map(String::toUpperCase)
                .filter(nome -> !encontrados.contains(nome))
                .toList();

        if (!naoEncontrados.isEmpty()) {
            throw new IllegalArgumentException("Permissões não encontradas: " + naoEncontrados);
        }
    }


}
