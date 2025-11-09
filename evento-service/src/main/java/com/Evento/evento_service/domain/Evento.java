package com.Evento.evento_service.domain;

import java.util.UUID;

public record Evento(
        UUID ID,
        String Local_evento,
        String Data_evento) {
}
