package com.example.usuariosApi.excecoes;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Execoes.EmailExistente;
import com.example.usuariosApi.Usuario.Execoes.UsuarioNaoExistente;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExcecoesTest {


    Usuario usuario;
    UsuarioDto usuarioDto;

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuariosService service;

    @BeforeEach
    void carregarUsuario(){
        usuario = new Usuario();
        usuario.setNome("gabriel");
        usuario.setEmail("Gladysongabriel12@gmail.com");
        usuario.setSenha("gabriel123");
        usuario.setRoles(List.of(Roles.USER));

        usuarioDto = new UsuarioDto(
                 usuario.getNome()
                ,usuario.getEmail()
                ,usuario.getSenha()
                ,usuario.getRoles());
    }

    @Test
    void testandoEmailJaExistenteException(){
        when(repository.existsByEmail(usuarioDto.email()))
                .thenReturn(true); 

        Assertions.assertThrows(EmailExistente.class,() -> service.salvar(usuarioDto) );
    }

    @Test
    void testandoUsuarioNaoExistente(){

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Assertions.assertThrows(UsuarioNaoExistente.class, () -> service.buscar(id));
    }
}
