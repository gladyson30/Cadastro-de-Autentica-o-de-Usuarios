package com.example.usuariosApi.Service;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Execoes.EmailExistente;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class Test222 {

    @Autowired
    UsuarioDto dto;

    @InjectMocks
    UsuariosService service;

    @Mock
    UsuarioRepository repository;

    @BeforeEach
    void setUp(){
        dto = new UsuarioDto(
                "gabriel",
                "gladysongabriel123@gmail.com",
                "gabriel123",
                List.of(Roles.USER)
        );
    }

    @Test
    void testarMetodoSalvar(){

        when(repository.existsByEmail(any())).thenReturn(true);

        Assertions.assertThrows(EmailExistente.class,() -> service.salvar(dto));
        verify(service).salvar(dto);
    }
}
