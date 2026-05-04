package com.example.usuariosApi.Usuario.security;

import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Execoes.UsuarioNaoExistente;
import com.example.usuariosApi.Usuario.Service.UsuariosService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAuthenticationProvider implements AuthenticationProvider {

    private final UsuariosService usuariosService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usuariosService.obterPorEmail(email);

        String senhaCriptografada = usuario.getSenha();

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario ou senha incorretos ");
        }

        boolean checarSenha = passwordEncoder.matches(senha,senhaCriptografada);

        if (checarSenha){
            return new UsuarioAuthentication(usuario);
        }
        
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}
