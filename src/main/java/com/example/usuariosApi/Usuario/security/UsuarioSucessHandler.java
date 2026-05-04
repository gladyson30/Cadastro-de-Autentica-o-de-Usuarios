package com.example.usuariosApi.Usuario.security;

import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException {

        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String nome = oAuth2User.getAttribute("name");

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null){
            salvarClienteGoogle(nome,email);
            usuario = usuarioRepository.findByEmail(email);
        }

        authentication = new UsuarioAuthentication(usuario);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response,authentication);
    }

    public void salvarClienteGoogle(String nome, String email){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senhaAleatoriaGoogle()));
        usuario.setRoles(List.of(Roles.valueOf("GOOGLE")));
        usuarioRepository.save(usuario);
    }

    public static String senhaAleatoriaGoogle(){
            String caracteres = "ABCDEFGHIJKLMNOPQR@#$%&*STUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            int tamanho = 10;

            SecureRandom random = new SecureRandom();
            StringBuilder resultado = new StringBuilder();

            for (int i = 0; i < tamanho; i++) {
                int indice = random.nextInt(caracteres.length());
                resultado.append(caracteres.charAt(indice));
            }

            return resultado.toString();
    }
}
// FilterChain chain,