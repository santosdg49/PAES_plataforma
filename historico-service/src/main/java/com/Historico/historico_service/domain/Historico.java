package com.Historico.historico_service.domain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Historico {

    private UUID UsuarioID;
    private UUID ID;
    private List<Evento> eventos;

    public Historico(UUID ID, UUID UsuarioID) {
        this.ID = ID;
        this.UsuarioID = UsuarioID;
        this.eventos = new ArrayList<>();
    }

    public UUID getID() {
        return ID;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public UUID getUsuarioID() {
        return UsuarioID;
    }
}
