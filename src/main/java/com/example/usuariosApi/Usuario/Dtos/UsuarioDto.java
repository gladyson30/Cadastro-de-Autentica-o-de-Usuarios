package com.example.usuariosApi.Usuario.Dtos;

import com.example.usuariosApi.Usuario.Entity.Roles;

import java.util.List;

public record UsuarioDto(
        String nome,
        String email,
        String senha,
        List<Roles> roles
) {
}
