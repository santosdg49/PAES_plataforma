package com.Cliente.cliente_service.domain;

import java.util.UUID;

public record Cliente(
    UUID ID,
    String nome,
    String endereco,
    String cnpj) {

}
