package com.sportify.backend.controller;

import com.sportify.backend.repository.EquipeRepository.EquipeAthleteCount;
import com.sportify.backend.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/athlete-counts")
    public ResponseEntity<List<EquipeAthleteCount>> getTmesEQuantidadeAtletas() {
        List<EquipeAthleteCount> teams = equipeService.getTmesEQuantidadeAtletas();
        return ResponseEntity.ok(teams);
    }
}
