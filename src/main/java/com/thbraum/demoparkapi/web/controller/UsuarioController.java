package com.thbraum.demoparkapi.web.controller;

import com.thbraum.demoparkapi.entity.Usuario;
import com.thbraum.demoparkapi.service.UsuarioService;
import com.thbraum.demoparkapi.web.dto.UsuarioCreateDto;
import com.thbraum.demoparkapi.web.dto.UsuarioResponseDto;
import com.thbraum.demoparkapi.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = usuarioService.buscarTodos();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Usuario user = usuarioService.buscarPorId(id);

            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            HttpStatusCode status = e.getStatusCode();
            String reason = e.getReason();
            return ResponseEntity.status(status).body(reason);
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario user = usuarioService.editarSenha(id, usuario.getPassword());

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

}
