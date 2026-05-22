package com.parksys.dto;

public record LoginResponse(
        String mensagem,
        String email,
        String role
) {
}
