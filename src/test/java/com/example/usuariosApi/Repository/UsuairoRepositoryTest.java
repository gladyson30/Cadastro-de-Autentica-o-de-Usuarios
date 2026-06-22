package com.example.usuariosApi.Repository;

import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

@DataJpaTest
@ActiveProfiles("test")
public class UsuairoRepositoryTest {

    Usuario usuario;

    @Autowired
    UsuarioRepository repository;

    @BeforeEach
    void carregarUsuario(){
        usuario = new Usuario();
        usuario.setNome("gabriel");
        usuario.setEmail("Gladysongabriel12@gmail.com");
        usuario.setSenha("gabriel123");
    }

    @Test
    void testandofindByEmail(){
        repository.save(usuario);

        Usuario encontrado = repository.findByEmail(usuario.getEmail());
        Assertions.assertNotNull(encontrado);
        Assertions.assertEquals("Gladysongabriel12@gmail.com",encontrado.getEmail());
    }

    @Test
    void deveRetornarTruequandoexistsByEmailForChamado(){
        repository.save(usuario);
        boolean encontrar = repository.existsByEmail(usuario.getEmail());
        Assertions.assertTrue(encontrar);
    }

    @Test
    void deveRetornarFalseQuandoExistsByEmailForChamado(){
        repository.save(usuario);
        boolean encontrar = repository.existsByEmail("emailNaoexistente@gmail.com");
        Assertions.assertFalse(encontrar);
    }


}
