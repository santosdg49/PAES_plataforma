package com.Historico.historico_service.api.dto;

import java.util.UUID;

public record EventoDTO(
        UUID EventoID,
        String Local_evento,
        String Data_evento,
        Double valor) {
}
