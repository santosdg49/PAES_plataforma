package com.Historico.historico_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record NovoEventoDTO(
        @NotBlank UUID ClienteID,
        @NotBlank UUID EventoID,
        @NotBlank String Local_evento,
        @NotBlank String Data_evento,
        @Positive Double valor) {
}
