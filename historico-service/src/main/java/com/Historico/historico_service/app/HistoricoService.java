package com.Historico.historico_service.app;

import com.Historico.historico_service.api.dto.EventoDTO;
import com.Historico.historico_service.api.dto.HistoricoDTO;
import com.Historico.historico_service.api.dto.NovoEventoDTO;
import com.Historico.historico_service.domain.Evento;
import com.Historico.historico_service.domain.Historico;
import com.Historico.historico_service.infra.EventoClient;
import com.Historico.historico_service.infra.HistoricoRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.Historico.historico_service.infra.EventoClient.*;

@Service
public class HistoricoService {

    private final HistoricoRepo repo;
    private final EventoClient eClient;

    public HistoricoService(HistoricoRepo repo, EventoClient eClient) {
        this.repo = repo;
        this.eClient = eClient;
    }

    public HistoricoDTO criarHistorico(UUID UsuarioID) {
        var h = new Historico(UUID.randomUUID(), UsuarioID);
        repo.save(h);
        return toDTO(h);
    }

    public HistoricoDTO obter(UUID id) {
        return toDTO(repo.byId(id));
    }

    public EventoDTO adicionarEvento(UUID historicoId, NovoEventoDTO in) {
        var hist = repo.byId(historicoId);


        var e = eClient.porId(in.EventoID());

        if (in.valor() <= 0) {
            throw new IllegalArgumentException("Valor deve ser > 0");
        }

        var Evento = new Evento(in.ClienteID(),e.id(), in.Local_evento(), e.Data_evento(), in.valor());

        hist.getEventos().add(Evento);
        repo.save(hist);

        return new EventoDTO(Evento.ClienteID(),Evento.EventoID(), Evento.Local_evento(), Evento.Data_evento(), Evento.valor());
    }



    private HistoricoDTO toDTO(Historico h) {
        List<EventoDTO> eventos = new ArrayList<>();
        for (var i : h.getEventos()) {
            eventos.add(new EventoDTO(i.ClienteID(),i.EventoID(), i.Local_evento(), i.Data_evento(), i.valor()));
        }
        return new HistoricoDTO(h.getUsuarioID(), h.getID(), eventos);
    }

    public void removerEvento(UUID historicoId, UUID eventoId) {
        var h = repo.byId(historicoId);
        var removed = h.getEventos().removeIf(i -> i.EventoID().equals(eventoId));
        if (!removed) throw new java.util.NoSuchElementException("Item " + eventoId + " não existe no histrico " + historicoId);
        repo.save(h);
    }
}
