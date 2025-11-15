package com.Historico.historico_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record NovoEventoDTO(
        @NotNull UUID EventoID,
        @NotBlank String Instituicao_responsavel,
        @NotBlank String Local_evento,
        @NotBlank String Data_evento,
        @Positive Double valor) {
}
