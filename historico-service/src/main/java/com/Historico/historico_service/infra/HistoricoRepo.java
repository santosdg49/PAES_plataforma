package com.Historico.historico_service.infra;

import com.Historico.historico_service.domain.Historico;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HistoricoRepo {
    private final Map<UUID, Historico> db = new ConcurrentHashMap<>();

    public Historico save(Historico h){
        db.put(h.getID(), h);
        return h;
    }

    public Historico byId(UUID id){
        var h = db.get(id);
        if (h == null) throw new NoSuchElementException("Historico "+id+" não existe");
        return h;
    }

    public List<Historico> findAll(){
        return new ArrayList<>(db.values());
    }
}
