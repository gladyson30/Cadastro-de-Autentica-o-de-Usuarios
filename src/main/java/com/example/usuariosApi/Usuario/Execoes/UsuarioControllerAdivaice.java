package com.example.usuariosApi.Usuario.Execoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UsuarioControllerAdivaice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> validacaoException(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> erros = new HashMap<>();
//
//        ex.getBindingResult()
//                .getFieldErrors()
//                .forEach(erro -> erros.put(
//                        erro.getField(),
//                        erro.getDefaultMessage()
//                ));
//
//        return ResponseEntity.status(400).body("erro de validação ");
//    }

    @ExceptionHandler(EmailExistente.class)
    public ResponseEntity<String> emailExistente(EmailExistente emailExistente){
        return ResponseEntity.status(409).body("Email ja cadastrado");
    }

    @ExceptionHandler(UsuarioNaoExistente.class)
    public ResponseEntity<String> usuarioNaoExistente(UsuarioNaoExistente usuarioNaoExistente){
        return ResponseEntity.status(404).body("Usuario não existente");
    }
}
