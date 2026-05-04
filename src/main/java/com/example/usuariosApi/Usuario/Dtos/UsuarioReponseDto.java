package com.example.usuariosApi.Usuario.Dtos;

import com.example.usuariosApi.Usuario.Entity.Roles;

import java.util.List;

public record UsuarioReponseDto(
        String nome,
        String email,
        List<Roles> roles
) {
}
