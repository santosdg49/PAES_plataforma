package com.Usuario.usuario_service.api;

import com.Usuario.usuario_service.api.dto.NovoUsuarioDTO;
import com.Usuario.usuario_service.api.dto.UsuarioDTO;
import com.Usuario.usuario_service.domain.Usuario;
import com.Usuario.usuario_service.infra.UsuarioRepo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plataforma/usuarios")
public class UsuarioController {
    private final UsuarioRepo banco;

    public UsuarioController(UsuarioRepo banco) {
        this.banco = banco;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return banco.findAll().stream().map(c -> new UsuarioDTO(c.ID(),
                c.nome(),
                c.endereco(),
                c.idade(),
                c.cpf())
        ).toList();
    }

    @GetMapping("/{id}")
    public UsuarioDTO porId(@PathVariable("id") UUID id) {
        var c = banco.byId(id);
        return new UsuarioDTO(c.ID(), c.nome(), c.endereco(), c.idade(), c.cpf());
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public UsuarioDTO criar(@Valid @RequestBody NovoUsuarioDTO dto) {
        var c = banco.save(new Usuario(java.util.UUID.randomUUID(), dto.nome(), dto.endereco(), dto.idade(), dto.cpf()));
        return new UsuarioDTO(c.ID(), c.nome(), c.endereco(), c.idade(), c.cpf());
    }
}
