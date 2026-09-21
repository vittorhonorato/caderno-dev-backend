package com.vittorhonorato.caderno_dev.dto.request;

public record RegisterRequestDTO(
        String email,
        String username,
        String password
) {
}
