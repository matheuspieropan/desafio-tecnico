package com.pieropan.getstore.service;

import com.pieropan.getstore.entity.Usuario;
import com.pieropan.getstore.repository.UsuarioRepository;
import com.pieropan.getstore.request.LoginRequest;
import com.pieropan.getstore.request.UsuarioCadastroRequest;
import com.pieropan.getstore.response.UsuarioCadastradoResponse;
import com.pieropan.getstore.security.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    AuthenticationManager authenticationManager;

    TokenService tokenService;

    UsuarioRepository repository;

    PasswordEncoder passwordEncoder;

    public String realizarLogin(LoginRequest loginRequest) {
        var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha()));

        Usuario usuario = (Usuario) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario.getEmail());
    }

    public UsuarioCadastradoResponse cadastrar(UsuarioCadastroRequest usuarioCadastroRequest) {
        String nome = usuarioCadastroRequest.nome();
        String email = usuarioCadastroRequest.email();
        String senha = passwordEncoder.encode(usuarioCadastroRequest.senha());

        Usuario usuario = repository.save(new Usuario(nome, email, senha));
        return new UsuarioCadastradoResponse(usuario.getNome(), usuario.getEmail());
    }
}