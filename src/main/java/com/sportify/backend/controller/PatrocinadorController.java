package com.sportify.backend.controller;

import com.sportify.backend.model.Patrocinador;
import com.sportify.backend.service.PatrocinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patrocinadores")
public class PatrocinadorController {

    private final PatrocinadorService patrocinadorService;

    @Autowired
    public PatrocinadorController(PatrocinadorService patrocinadorService) {
        this.patrocinadorService = patrocinadorService;
    }

    @GetMapping("/all-event-types")
    public ResponseEntity<List<Patrocinador>> getSponsorsOfAllEventTypes() {
        List<Patrocinador> sponsors = patrocinadorService.getSponsorsOfAllEventTypes();
        return ResponseEntity.ok(sponsors);
    }
}
