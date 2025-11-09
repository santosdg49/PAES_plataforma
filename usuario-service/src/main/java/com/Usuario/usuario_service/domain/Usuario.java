package com.Usuario.usuario_service.domain;

import java.util.UUID;

public record Usuario(
        UUID ID,
        String nome,
        String endereco,
        int idade,
        String cpf) {
}
