package com.parksys.controller;

import com.parksys.dto.LoginRequest;
import com.parksys.dto.LoginResponse;
import com.parksys.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/registrar")
    public ResponseEntity<LoginResponse> registrar(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(201).body(service.registrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}