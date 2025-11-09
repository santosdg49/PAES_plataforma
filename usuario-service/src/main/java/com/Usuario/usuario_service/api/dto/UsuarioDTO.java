package com.Usuario.usuario_service.api.dto;

import java.util.UUID;

public record UsuarioDTO(
        UUID ID,
        String nome,
        String endereco,
        int idade,
        String cpf) {
}
