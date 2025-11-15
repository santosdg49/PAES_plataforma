package com.Historico.historico_service.api;

import com.Historico.historico_service.api.dto.EventoDTO;
import com.Historico.historico_service.api.dto.HistoricoDTO;
import com.Historico.historico_service.api.dto.NovoEventoDTO;
import com.Historico.historico_service.api.dto.NovoHistoricoDTO;
import com.Historico.historico_service.app.HistoricoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/plataforma/historicos")
public class HistoricoController {

    private final HistoricoService service;

    public HistoricoController(HistoricoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoDTO criar(@Valid @RequestBody NovoHistoricoDTO h) {
        return service.criarHistorico(h.UsuarioID());
    }

    @GetMapping("/{id}")
    public HistoricoDTO porId(@PathVariable("id") UUID id){
        return service.obter(id);
    }

    @PostMapping("/{id}/eventos")
    @ResponseStatus(HttpStatus.CREATED)
    public EventoDTO addEvento(@PathVariable("id") UUID id, @Valid @RequestBody NovoEventoDTO in){
        return service.adicionarEvento(id, in);
    }

    @DeleteMapping("/{id}/eventos/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delEvento(@PathVariable("id") UUID id, @PathVariable("eventoID") UUID eventoId){
        service.removerEvento(id, eventoId);
    }

    @GetMapping
    public List<HistoricoDTO> listarHistoricos(){
        return service.listarTodos();
    }
}
