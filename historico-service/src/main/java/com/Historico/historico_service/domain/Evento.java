package com.Historico.historico_service.domain;

import java.util.UUID;

public record Evento(
        UUID EventoID,
        String Instituicao_responsavel,
        String Local_evento,
        String Data_evento,
        Double valor) {
}
