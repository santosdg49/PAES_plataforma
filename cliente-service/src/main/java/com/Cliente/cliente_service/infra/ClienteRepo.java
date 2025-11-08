package com.Cliente.cliente_service.infra;

import com.Cliente.cliente_service.domain.Cliente;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClienteRepo {

private final Map<UUID, Cliente> db =  new ConcurrentHashMap<>();

    public List<Cliente> findAll() {
        return new ArrayList<>(db.values());
    }
    public Cliente byId(UUID id) {
        return Optional.ofNullable(db.get(id)).orElseThrow();
    }

    public Cliente save(Cliente c) {
        db.put(c.ID(), c);
        return c;
    }
}
