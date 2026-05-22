package com.parksys.service;

import com.parksys.dto.LoginRequest;
import com.parksys.dto.LoginResponse;
import com.parksys.exception.BusinessException;
import com.parksys.model.Usuario;
import com.parksys.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse registrar(LoginRequest request) {

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email ja cadastrado: " + request.email());
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.email());
        usuario.setSenhaHash(passwordEncoder.encode(request.senha()));

        usuarioRepository.save(usuario);

        return new LoginResponse("Usuario criado com sucesso", usuario.getEmail(), usuario.getRole());
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Credenciais invalidas"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenhaHash())) {
            throw new BusinessException("Credenciais invalidas");
        }

        return new LoginResponse("Login realizado com sucesso", usuario.getEmail(), usuario.getRole());
    }
}