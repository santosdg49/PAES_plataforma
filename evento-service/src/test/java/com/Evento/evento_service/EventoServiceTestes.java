package com.Evento.evento_service;
import com.Evento.evento_service.domain.Evento;
import com.Evento.evento_service.infra.EventoRepo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EventoServiceTestes {

    @Test
    public void testeAtributosNulosAoCriarEvento(){
        Evento evento = new Evento(UUID.randomUUID(), "Inst teste",
                "Localidade teste", "15/11/2025",
                1000.00);

        assertNotNull(evento.ID());
        assertNotNull(evento.instituicao_responsavel());
        assertNotNull(evento.Local_evento());
        assertNotNull(evento.Data_evento());
        assertNotNull(evento.valor());
    }

    @Test
    public void testeSalvarEventoNoRepo(){
        EventoRepo repo =  new EventoRepo();
        Evento evento = new Evento(UUID.randomUUID(), "Inst teste",
                "Localidade teste", "15/11/2025",
                1000.00);

        Evento eventoSalvo = repo.save(evento);

        assertEquals(eventoSalvo, evento);
    }

    @Test
    public void testePesquisarEventoNoRepo(){
        EventoRepo repo =  new EventoRepo();
        Evento evento = new Evento(UUID.randomUUID(), "Inst teste",
                "Localidade teste", "15/11/2025",
                1000.00);

        repo.save(evento);

        Evento eventoBuscado = repo.byId(evento.ID());

        assertEquals(eventoBuscado, evento);
    }
}
