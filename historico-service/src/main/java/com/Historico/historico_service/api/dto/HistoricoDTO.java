package com.Historico.historico_service.api.dto;

import java.util.List;
import java.util.UUID;

public record HistoricoDTO(
        UUID ID,
        List<EventoDTO> eventos) {
}


