package com.Usuario.usuario_service;
import com.Usuario.usuario_service.domain.Usuario;
import com.Usuario.usuario_service.infra.UsuarioRepo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTests {

    @Test
    public void testeAtributosNulosAoCriarUsuario(){
        Usuario user = new Usuario(UUID.randomUUID(), "Jonas",
                "Vale dos Machados", 40, "14576589090");

        assertNotNull(user.ID());
        assertNotNull(user.nome());
        assertNotNull(user.endereco());
        assertNotNull(user.idade());
        assertNotNull(user.cpf());
    }

    @Test
    public void testeSalvarUsuarioNoRepo(){
        Usuario u = new Usuario(UUID.randomUUID(), "Diego", "Vale dos Machados",
                22, "12345678990");
        UsuarioRepo uRepo = new UsuarioRepo();
        Usuario userSalvo = uRepo.save(u);
        assertEquals(u, userSalvo);
    }

    @Test
    public void testePesquisarUsuarioNoRepo(){
        UsuarioRepo uRepo = new UsuarioRepo();
        Usuario u = new Usuario(UUID.randomUUID(), "Diego", "Vale dos Machados",
                22, "12345678990");
        uRepo.save(u);
        Usuario usuarioPesquisado = uRepo.byId(u.ID());

        assertEquals(u, usuarioPesquisado);
    }
}
