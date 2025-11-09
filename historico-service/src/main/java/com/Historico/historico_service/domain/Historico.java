package com.Historico.historico_service.domain;

import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

public class Historico {

    private UUID ID;
    private List<Evento> eventos;

    public Historico(UUID ID) {
        this.ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
