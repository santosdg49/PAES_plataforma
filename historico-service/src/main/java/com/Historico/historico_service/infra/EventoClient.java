package com.Historico.historico_service.infra;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class EventoClient {

    public record EventoDTO(UUID id, String Local, String Data_evento, Double valor) {}
    private RestClient rest = null; //Cliente HTTP sincrono do Spring

    public EventoClient(@Value("${eventos.url}") String baseUrl){
        var f = new SimpleClientHttpRequestFactory();
        f.setConnectTimeout(2000);
        f.setReadTimeout(2000);
        this.rest = RestClient.builder().baseUrl(baseUrl).requestFactory(f).build();
    }

    public EventoDTO porId(UUID id){
        try {
            return rest.get().uri("/eventos/{id}", id).retrieve().body(EventoDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Evento inexistente: " + id);
        }
    }
}
