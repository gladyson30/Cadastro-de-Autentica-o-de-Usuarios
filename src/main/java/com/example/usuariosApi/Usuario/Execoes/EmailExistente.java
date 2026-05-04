package com.example.usuariosApi.Usuario.Execoes;

public class EmailExistente extends RuntimeException {
    public EmailExistente(String message) {
        super(message);
    }
}
