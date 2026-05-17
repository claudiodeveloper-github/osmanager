package com.osmanager.dto;

import com.osmanager.entity.Role;
import com.osmanager.entity.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

    private final Long id;
    private final String nome;
    private final String email;
    private final Role role;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }
}