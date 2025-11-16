package com.Cliente.cliente_service;

import com.Cliente.cliente_service.domain.Cliente;
import com.Cliente.cliente_service.infra.ClienteRepo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class ClienteServiceTests {

    @Test
    public void testeAtributosNulosAoCriarCliente(){
        Cliente cliente = new Cliente(UUID.randomUUID(), "Instituição Teste",
                "Bairro X, Rua Y - abc", "11.222.333/4444-55");

        assertNotNull(cliente.ID());
        assertNotNull(cliente.nome());
        assertNotNull(cliente.endereco());
        assertNotNull(cliente.cnpj());
    }

    @Test
    public void testeSalvarClienteNoRepo(){

        Cliente cliente = new Cliente(UUID.randomUUID(), "Instituição Teste",
                "Bairro X, Rua Y - abc", "11.222.333/4444-55");

        ClienteRepo Repo = new ClienteRepo();
        Cliente clienteSalvoRepo = Repo.save(cliente);
        assertEquals(cliente, clienteSalvoRepo);
    }

    @Test
    public void testePesquisarUsuarioNoRepo(){
        ClienteRepo Repo = new ClienteRepo();
        Cliente cliente = new Cliente(UUID.randomUUID(), "Instituição Teste",
                "Bairro X, Rua Y - abc", "11.222.333/4444-55");
        Repo.save(cliente);

        Cliente clienteBuscado = Repo.byId(cliente.ID());
        assertEquals(cliente, clienteBuscado);
    }
}
