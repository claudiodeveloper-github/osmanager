package com.osmanager.service;

import com.osmanager.entity.Usuario;
import com.osmanager.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Usuario salvarUsuario(Usuario usuario) {

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );

        return repository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public boolean autenticar(String email, String senha) {

        Usuario usuario = repository.findByEmail(email)
                .orElse(null);

        if (usuario == null) {
            return false;
        }

        return passwordEncoder.matches(
                senha,
                usuario.getSenha()
        );
    }
}