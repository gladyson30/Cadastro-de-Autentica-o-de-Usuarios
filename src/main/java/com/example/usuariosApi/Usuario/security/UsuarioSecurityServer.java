package com.example.usuariosApi.Usuario.security;

import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSecurityServer {

    private final UsuariosService usuariosService;

    public Usuario obterUsuarioLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsuarioAuthentication authentication1){
            return (Usuario) authentication1.getUsuario();
        }
        return null;
    }
}
