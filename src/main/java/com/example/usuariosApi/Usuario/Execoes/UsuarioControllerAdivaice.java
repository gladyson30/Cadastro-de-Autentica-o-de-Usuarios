package com.example.usuariosApi.Usuario.Execoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UsuarioControllerAdivaice {

    @ExceptionHandler(EmailExistente.class)
    public ResponseEntity<String> emailExistente(EmailExistente emailExistente){
        return ResponseEntity.status(409).body("Email ja cadastrado");
    }

    @ExceptionHandler(UsuarioNaoExistente.class)
    public ResponseEntity<String> usuarioNaoExistente(UsuarioNaoExistente usuarioNaoExistente){
        return ResponseEntity.status(404).body("Usuario não existente");
    }
}
