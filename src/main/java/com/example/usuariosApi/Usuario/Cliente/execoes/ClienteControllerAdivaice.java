package com.example.usuariosApi.Usuario.Cliente.execoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteControllerAdivaice {

    @ExceptionHandler(ClienteJaPossuiCadastro.class)
    public ResponseEntity<String> clinentePossuiCadastro(ClienteJaPossuiCadastro clienteJaPossuiCadastro){
        return ResponseEntity.status(409).body("cliente já possui cadastro ");
    }


    @ExceptionHandler(ClienteNaoExiste.class)
    public ResponseEntity<String> clienteNaoExiste(ClienteNaoExiste clienteNaoExiste){
        return ResponseEntity.status(404).body("cliente nao existe ");
    }
}
