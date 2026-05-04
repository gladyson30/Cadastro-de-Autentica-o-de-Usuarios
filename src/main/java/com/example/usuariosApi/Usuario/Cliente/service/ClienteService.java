package com.example.usuariosApi.Usuario.Cliente.service;

import com.example.usuariosApi.Usuario.Cliente.entity.Cliente;
import com.example.usuariosApi.Usuario.Cliente.execoes.ClienteJaPossuiCadastro;
import com.example.usuariosApi.Usuario.Cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    public Cliente salvar(Cliente cliente){
        if(clienteRepository.existsByClienteid(cliente.getClienteid())){
            throw new ClienteJaPossuiCadastro("Cliente ja possui cadastro");
        }
        String senhaCriptografada = passwordEncoder.encode(cliente.getClienteSecret());
        cliente.setClienteSecret(senhaCriptografada);
        clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente obterporClienteId(String clienteid){
        return clienteRepository.findByClienteid(clienteid);
    }


}
