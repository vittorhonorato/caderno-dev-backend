package com.vittorhonorato.caderno_dev.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CadernoRequestDTO(
        @NotBlank
        String assunto,

        @NotBlank
        String resumo,

        String categoria
) {
}
