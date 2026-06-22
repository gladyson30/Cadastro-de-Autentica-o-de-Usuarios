package com.example.usuariosApi.Usuario.controller;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Dtos.UsuarioReponseDto;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody @Valid  UsuarioDto usuarioDto){
        usuariosService.salvar(usuarioDto);
        return ResponseEntity.status(201).body("Usuario cadastrado");
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioReponseDto> buscar(@PathVariable @NotBlank UUID id){
        UsuarioReponseDto usuarioReponseDto = usuariosService.buscar(id);
        return ResponseEntity.ok(usuarioReponseDto);
    }


    @GetMapping("listar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UsuarioReponseDto>> listar(){
        return ResponseEntity.ok(usuariosService.listar());
    }

    @PutMapping("{id}")
    public ResponseEntity<String> atualixar(@PathVariable UUID id,@RequestBody UsuarioDto usuarioDto){
        usuariosService.atualizar(id,usuarioDto);
        return ResponseEntity.status(200).body("Usuario atualizado");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id){
        usuariosService.deletar(id);
        return ResponseEntity.status(204).body("usuario deletado");
    }
}
