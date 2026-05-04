package com.example.usuariosApi.Usuario.Repository;

import com.example.usuariosApi.Usuario.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByEmail(String email);
    Usuario findByEmail(String email);
}
