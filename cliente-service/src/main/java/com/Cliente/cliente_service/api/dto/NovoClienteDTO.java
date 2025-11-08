package com.Cliente.cliente_service.api.dto;

import jakarta.validation.constraints.NotBlank;

public record NovoClienteDTO(
        @NotBlank String nome,
        @NotBlank String endereco,
        @NotBlank String cnpj) {
}
