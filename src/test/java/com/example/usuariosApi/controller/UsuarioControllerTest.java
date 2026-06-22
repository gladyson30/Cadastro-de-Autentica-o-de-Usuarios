package com.example.usuariosApi.controller;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Dtos.UsuarioReponseDto;
import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import com.example.usuariosApi.Usuario.controller.UsuarioController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    UsuarioDto dto;
    private String json;

    @Autowired
    MockMvc mvc;

    @MockitoBean
    UsuariosService usuariosService;

    @BeforeEach
    void CarregandoUSuario(){

        dto = new UsuarioDto(
                "gabriel",
                "Gladysongabriel199@gmail.com",
                "Gabriel1234",
                List.of(Roles.USER)
        );

         json = """
                {
                    "nome": "gabriel",
                    "email": "Gladysongabriel199@gmail.com",
                    "senha": "Gabriel1234",
                    "roles": ["USER"]
                }
                """;

    }

    @Test
    void deveSalvaUsuario() throws Exception{
        UsuarioReponseDto usuarioReponseDto = new UsuarioReponseDto(
                "gabriel",
                "Gladysongabriel199@gmail.com",
                List.of(Roles.USER));
        when(usuariosService.salvar(any(UsuarioDto.class))).thenReturn(usuarioReponseDto);

        ResultActions resultado = mvc.perform(
                MockMvcRequestBuilders
                        .post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        );

        resultado.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content().string("Usuario cadastrado"));

    }

}
