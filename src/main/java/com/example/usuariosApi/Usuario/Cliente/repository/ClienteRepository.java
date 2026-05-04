package com.example.usuariosApi.Usuario.Cliente.repository;

import com.example.usuariosApi.Usuario.Cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Cliente findByClienteid(String clienteid);
    boolean existsByClienteid(String clienteid);
}
