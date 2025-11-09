package com.Evento.evento_service.infra;

import com.Evento.evento_service.domain.Evento;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventoRepo {
    private final Map<UUID, Evento> db =new ConcurrentHashMap<>();

    public ArrayList<Evento> findAll(){
        return new ArrayList<>(this.db.values());
    }

    public Evento byId(UUID id){
        return Optional.ofNullable(db.get(id)).orElseThrow();
    }

    public Evento save(Evento c) {
        db.put(c.ID(), c);
        return c;
    }
}
