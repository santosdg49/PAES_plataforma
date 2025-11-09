package com.Usuario.usuario_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record NovoUsuarioDTO(
        @NotBlank String nome,
        @NotBlank String endereco,
        @Positive int idade,
        @NotBlank String cpf) {
}
