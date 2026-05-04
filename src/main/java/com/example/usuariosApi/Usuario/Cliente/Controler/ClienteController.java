package com.example.usuariosApi.Usuario.Cliente.Controler;

import com.example.usuariosApi.Usuario.Cliente.entity.Cliente;
import com.example.usuariosApi.Usuario.Cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Cliente cliente){
        Cliente cliente1 = clienteService.salvar(cliente);
    }

}
