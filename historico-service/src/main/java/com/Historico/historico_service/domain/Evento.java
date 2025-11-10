package com.Historico.historico_service.domain;

import java.util.UUID;

public record Evento(
        UUID ClienteID,
        UUID EventoID,
        String Local_evento,
        String Data_evento,
        Double valor) {
}
