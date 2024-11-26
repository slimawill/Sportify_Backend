package com.sportify.backend.controller;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/patrocinador/{cnpj}/atletas")
    public ResponseEntity<List<Atleta>> getAtletasPeloPatrocinador(@PathVariable String cnpj) {
        List<Atleta> athletes = eventoService.getAtletasPeloPatrocinador(cnpj);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/modalidade/{modalidade}/atletas")
    public ResponseEntity<List<Atleta>> getAtletasPorModalidade(@PathVariable String modalidade) {
        List<Atleta> athletes = eventoService.getAtletasPorModalidade(modalidade);
        return ResponseEntity.ok(athletes);
    }
}
