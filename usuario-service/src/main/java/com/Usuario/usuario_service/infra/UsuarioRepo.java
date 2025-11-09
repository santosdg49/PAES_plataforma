package com.Usuario.usuario_service.infra;

import com.Usuario.usuario_service.domain.Usuario;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UsuarioRepo {

    private final Map<UUID, Usuario> db =  new ConcurrentHashMap<>();

    public List<Usuario> findAll() {
        return new ArrayList<>(db.values());
    }
    public Usuario byId(UUID id) {
        return Optional.ofNullable(db.get(id)).orElseThrow();
    }

    public Usuario save(Usuario u) {
        db.put(u.ID(), u);
        return u;
    }
}
