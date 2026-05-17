package com.osmanager.controller;

import com.osmanager.dto.UsuarioDTO;
import com.osmanager.entity.Usuario;
import com.osmanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(new UsuarioDTO(salvo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> lista = usuarioService.listarUsuarios()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
        return ResponseEntity.ok(lista);
    }
}