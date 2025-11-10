package com.Historico.historico_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NovoHistoricoDTO(
        @NotNull UUID UsuarioID) {
}
