package com.Evento.evento_service.api.dto;

import java.util.UUID;

public record EventoDTO(
        UUID ID,
        String Local_evento,
        String Data_evento) {
}
