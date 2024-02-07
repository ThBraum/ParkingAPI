package com.thbraum.demoparkapi.web.controller;

import com.thbraum.demoparkapi.entity.Usuario;
import com.thbraum.demoparkapi.service.UsuarioService;
import com.thbraum.demoparkapi.web.dto.UsuarioCreateDto;
import com.thbraum.demoparkapi.web.dto.UsuarioResponseDto;
import com.thbraum.demoparkapi.web.dto.UsuarioSenhaDto;
import com.thbraum.demoparkapi.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> usuarios = usuarioService.buscarTodos();

        return ResponseEntity.ok(UsuarioMapper.toListDto(usuarios));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDto usuario) {
        Usuario user = usuarioService.editarSenha(id, usuario.getSenhaAtual(), usuario.getNovaSenha(), usuario.getConfirmacaoSenha());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

}
