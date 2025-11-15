package com.Evento.evento_service.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EventoDTO(
        UUID ID,
        String instituicao_responsavel,
        String Local_evento,
        String Data_evento,
        Double valor) {
}
