package com.vittorhonorato.caderno_dev.dto.response;

import java.time.LocalDateTime;

public record CadernoResponseDTO (
        Long id,
        String assunto,
        String resumo,
        String categoria,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
