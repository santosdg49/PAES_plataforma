package com.Cliente.cliente_service.api.dto;

import java.util.UUID;

public record ClienteDTO(
        UUID ID,
        String nome,
        String endereco,
        String cnpj) {
}
