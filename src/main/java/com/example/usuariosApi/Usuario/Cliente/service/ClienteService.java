package com.example.usuariosApi.Usuario.Cliente.service;

import com.example.usuariosApi.Usuario.Cliente.Dtos.ClienteDto;
import com.example.usuariosApi.Usuario.Cliente.entity.Cliente;
import com.example.usuariosApi.Usuario.Cliente.execoes.ClienteJaPossuiCadastro;
import com.example.usuariosApi.Usuario.Cliente.execoes.ClienteNaoExiste;
import com.example.usuariosApi.Usuario.Cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;


    public void salvar(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setClienteSecret(passwordEncoder.encode(clienteDto.clienteSecret()));
        cliente.setClienteid(clienteDto.clienteid());
        cliente.setRedirectURI(clienteDto.redirectURI());
        cliente.setScope(clienteDto.scope());
        clienteRepository.save(cliente);
    }

    public void deletar(UUID id){
        if (!clienteRepository.existsById(id)){
            throw new ClienteNaoExiste("");
        }
        clienteRepository.deleteById(id);
    }

    public Cliente obterporClienteId(String clienteid){
        return clienteRepository.findByClienteid(clienteid);
    }


}
