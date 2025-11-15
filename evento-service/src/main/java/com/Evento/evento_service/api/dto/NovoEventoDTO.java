package com.Evento.evento_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record NovoEventoDTO(
        @NotBlank String instituicao_responsavel,
        @NotBlank String Local_evento,
        @NotBlank String Data_evento,
        @Positive Double valor) {
}
