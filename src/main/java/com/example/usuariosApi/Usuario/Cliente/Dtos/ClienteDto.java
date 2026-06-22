package com.example.usuariosApi.Usuario.Cliente.Dtos;

public record ClienteDto(
         String clienteid,
         String clienteSecret,
         String redirectURI,
         String scope
) {
}
