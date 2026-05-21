package com.example.usuariosApi.Usuario.Cliente.execoes;

public class ClienteNaoExiste extends RuntimeException {
  public ClienteNaoExiste(String message) {
    super(message);
  }
}
