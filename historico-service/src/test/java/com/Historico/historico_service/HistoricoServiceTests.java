package com.Historico.historico_service;

import com.Historico.historico_service.domain.Evento;
import com.Historico.historico_service.domain.Historico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;


public class HistoricoServiceTests {

    @Test
    public void testeCriarHistoricoIsercaoUsuarioID(){
        UUID UsuarioID = UUID.randomUUID();
        Historico hist = new Historico(UUID.randomUUID(),UsuarioID);
        assertEquals(UsuarioID,hist.getUsuarioID());

    }

    @Test
    public void testeAdicionarEventoNoHistorico(){
        Evento evento = new Evento(UUID.randomUUID(), "InstTest",
                "LocalTest", "15/11/2025", 1000.00);

        UUID UsuarioID = UUID.randomUUID();
        UUID histID = UUID.randomUUID();

        Historico hist = new Historico(histID,UsuarioID);
        hist.getEventos().add(evento);

        assertEquals(1,hist.getEventos().size());

    }

    @Test
    public void testeHistoricoNulo(){
        UUID UsuarioID = UUID.randomUUID();
        Historico hist = new Historico(UUID.randomUUID(),UsuarioID);
        assertNotNull(hist);
    }

    @Test
    public void testeListaEventosNula(){
        UUID UsuarioID = UUID.randomUUID();
        Historico hist = new Historico(UUID.randomUUID(),UsuarioID);
        assertNotNull(hist.getEventos());
    }


}
