package com.Cliente.cliente_service.api;

import com.Cliente.cliente_service.api.dto.NovoClienteDTO;
import com.Cliente.cliente_service.domain.Cliente;
import com.Cliente.cliente_service.infra.ClienteRepo;
import com.Cliente.cliente_service.api.dto.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plataforma/clientes")
public class ClienteController {
    private final ClienteRepo banco;

    public ClienteController(ClienteRepo banco) {
        this.banco = banco;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return banco.findAll().stream().map(c -> new ClienteDTO(c.ID(),
                c.nome(),
                c.endereco(),
                c.cnpj())
        ).toList();
    }

    @GetMapping("/{id}")
    public ClienteDTO porId(@PathVariable("id") UUID id) {
        var c = banco.byId(id);
        return new ClienteDTO(c.ID(), c.nome(), c.endereco(), c.cnpj());
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public ClienteDTO criar(@Valid @RequestBody NovoClienteDTO dto) {
        var c = banco.save(new Cliente(java.util.UUID.randomUUID(), dto.nome(), dto.endereco(), dto.cnpj()));
        return new ClienteDTO(c.ID(), c.nome(), c.endereco(), c.cnpj());
    }
}
