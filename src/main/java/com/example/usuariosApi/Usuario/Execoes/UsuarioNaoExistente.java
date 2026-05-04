package com.example.usuariosApi.Usuario.Execoes;

public class UsuarioNaoExistente extends RuntimeException {
    public UsuarioNaoExistente(String message) {
        super(message);
    }
}
