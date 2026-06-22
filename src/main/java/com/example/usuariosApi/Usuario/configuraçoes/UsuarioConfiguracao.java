package com.example.usuariosApi.Usuario.configuraçoes;

import com.example.usuariosApi.Usuario.security.UsuarioSucessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UsuarioConfiguracao {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security, UsuarioSucessHandler handler){
        return security
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(autorizar ->{
                    autorizar.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
                    autorizar.requestMatchers(HttpMethod.GET,"/usuarios/**").permitAll();
                    autorizar.requestMatchers(HttpMethod.POST, "/clientes/**").permitAll();

                    autorizar.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(handler)
                )
                .oauth2ResourceServer(resource ->
                        resource.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
