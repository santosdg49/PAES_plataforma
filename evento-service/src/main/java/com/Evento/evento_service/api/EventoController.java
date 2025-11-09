package com.Evento.evento_service.api;

import com.Evento.evento_service.api.dto.EventoDTO;
import com.Evento.evento_service.domain.Evento;
import com.Evento.evento_service.infra.EventoRepo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Plataforma/Eventos")
public class EventoController{
    private final EventoRepo banco;

    public EventoController(EventoRepo banco) {
        this.banco = banco;
    }


    @GetMapping("/{id}")
    public EventoDTO porId(@PathVariable("id") UUID id) {
        var e = banco.byId(id);
        return new EventoDTO(e.ID(), e.Local_evento(), e.Data_evento());
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public EventoDTO criar(@Valid @RequestBody EventoDTO dto) {
        var e = banco.save(new Evento(java.util.UUID.randomUUID(), dto.Local_evento(), dto.Data_evento()));
        return new EventoDTO(e.ID(), e.Local_evento(), e.Data_evento());
    }
}
