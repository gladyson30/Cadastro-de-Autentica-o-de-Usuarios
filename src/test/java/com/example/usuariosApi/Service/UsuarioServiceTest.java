package com.example.usuariosApi.Service;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Dtos.UsuarioReponseDto;
import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Execoes.EmailExistente;
import com.example.usuariosApi.Usuario.Execoes.UsuarioNaoExistente;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    UsuarioDto dto;
    Usuario usuario;
    List<Usuario> listarUsuarios;


    @Mock
    UsuarioRepository repository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UsuariosService service;
    @BeforeEach
    void carregarUsuario(){
        dto = new UsuarioDto(
                "gabriel",
                "Gladysongabriel199@gmail.com",
                "Gabriel1234",
                List.of(Roles.USER)
        );

        usuario = new Usuario();
        usuario.setNome("gabriel");
        usuario.setEmail("Gladysongabriel199@gmail.com");
        usuario.setSenha("Gabriel1234");
        usuario.setRoles(List.of(Roles.USER));

        Usuario usuario2 = new Usuario();
        usuario2.setNome("gabriel");
        usuario2.setEmail("Gladysongabriel199@gmail.com");
        usuario2.setSenha("Gabriel1234");
        usuario2.setRoles(List.of(Roles.USER));
        listarUsuarios = List.of(usuario,usuario2);
    }

    @DisplayName("testando o repository.save do service salvar")
    @Test
    void salvandoUsuarioServiceSalvar(){
        when(repository.save(Mockito.any())).thenReturn(usuario);

        UsuarioReponseDto usuario1 = service.salvar(dto);

        Assertions.assertEquals("Gladysongabriel199@gmail.com",usuario1.email());
    }

    @DisplayName("testando exceçao do metodo salvar")
    @Test
    void metodoSalvarServiceExcecao(){
        when(repository.existsByEmail(Mockito.any())).thenReturn(true);
        Assertions.assertThrows(EmailExistente.class,() -> service.salvar(dto));
    }

    @DisplayName("testando execao meotodo buscar")
    @Test
    void excecaoServiceBuscar(){

        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenThrow(UsuarioNaoExistente.class);

        Assertions.assertThrows(UsuarioNaoExistente.class,() ->service.buscar(id));

    }

    @DisplayName("chamando usuario no metodo buscar")
    @Test
    void trazendoUsuarioMetodoBuscar(){
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioReponseDto dto1 = service.buscar(id);

        Assertions.assertEquals("gabriel",dto1.nome());

    }

    @DisplayName("excecao metodo atualizar UsuarioNaoExistente")
    @Test
    void execaoMetodoAtualizar(){

        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(UsuarioNaoExistente.class,() ->service.atualizar(id,dto));
    }

    @Test
    void carrecarListaUsuarios(){
        when(repository.findAll()).thenReturn(listarUsuarios);

        List<UsuarioReponseDto> resultado = service.listar();

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void deletarUsuarioExistente(){

        UUID id = UUID.randomUUID();

        when(repository.existsById(id)).thenReturn(true);

        service.deletar(id);

        verify(repository).deleteById(id);
    }




}
