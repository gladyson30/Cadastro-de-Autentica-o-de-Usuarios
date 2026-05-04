package com.example.usuariosApi.Usuario.Service;

import com.example.usuariosApi.Usuario.Dtos.UsuarioDto;
import com.example.usuariosApi.Usuario.Dtos.UsuarioReponseDto;
import com.example.usuariosApi.Usuario.Entity.Roles;
import com.example.usuariosApi.Usuario.Entity.Usuario;
import com.example.usuariosApi.Usuario.Execoes.EmailExistente;
import com.example.usuariosApi.Usuario.Execoes.UsuarioNaoExistente;
import com.example.usuariosApi.Usuario.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class UsuariosService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void salvar(UsuarioDto usuarioDto){
        if (usuarioRepository.existsByEmail(usuarioDto.email())) {
            throw new EmailExistente("Usuarios já tem cadastro");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDto.senha()));
        usuario.setRoles(usuarioDto.roles());
        usuarioRepository.save(usuario);
    }

    public UsuarioReponseDto buscar(UUID id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoExistente(""));
        return new UsuarioReponseDto(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
                );
    }

    public Usuario obterPorEmail(String email){
       return usuarioRepository.findByEmail(email);
    }

    public void atualizar(UUID id,UsuarioDto usuarioDto){
        Usuario usuario = new Usuario();

        if (usuarioRepository.existsById(id)){
            throw new UsuarioNaoExistente("");
        }
        if (usuarioDto.email() != null && usuarioDto.email().trim().isEmpty()){
            usuario.setEmail(usuarioDto.email());
        }
        if (usuarioDto.nome() != null && usuarioDto.nome().trim().isEmpty()){
            usuario.setNome(usuarioDto.nome());
        }
        if (usuarioDto.senha()  != null && usuarioDto.senha().trim().isEmpty()){
            usuario.setSenha(passwordEncoder.encode(usuarioDto.senha()));
        }
        if (usuarioDto.roles() != null){
            usuario.setRoles(usuarioDto.roles());
        }
        if (usuarioRepository.existsByEmail(usuarioDto.email())) {
            throw new EmailExistente("");
        }
        usuarioRepository.save(usuario);

    }

    public List<UsuarioReponseDto> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioReponseDto(
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getRoles()
                ))
                .toList();
    }

    public void deletar(UUID id){
        if (usuarioRepository.existsById(id)){
            throw new UsuarioNaoExistente("");
        }
        usuarioRepository.deleteById(id);
    }
}
