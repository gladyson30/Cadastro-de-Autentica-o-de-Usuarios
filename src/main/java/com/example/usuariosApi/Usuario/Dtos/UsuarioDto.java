package com.example.usuariosApi.Usuario.Dtos;

import com.example.usuariosApi.Usuario.Entity.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDto(
        String nome,
        @Email(message = "campo obrigatorio")
        String email,
        @Size(min = 8, max = 20)
        @NotBlank(message = "campo obrigatorio")
        String senha,

        List<Roles> roles
) {
}
