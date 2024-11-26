package com.sportify.backend.controller;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.repository.AtletaRepository.AtletaHabilidadeCount;
import com.sportify.backend.service.AtletaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    private final AtletaService atletaService;

    @Autowired
    public AtletaController(AtletaService atletaService) {
        this.atletaService = atletaService;
    }

    @GetMapping("/habilidades")
    public ResponseEntity<List<AtletaHabilidadeCount>> getAtletasComMultiplasHabilidades() {
        List<AtletaHabilidadeCount> athletes = atletaService.getAtletasComMultiplasHabilidades();
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/esporte/{esporte}")
    public ResponseEntity<List<Atleta>> getAtletasPeloEsporte(@PathVariable String esporte) {
        List<Atleta> athletes = atletaService.getAtletasPeloEsporte(esporte);
        return ResponseEntity.ok(athletes);
    }

    @PostMapping("/login")
    public ResponseEntity<Atleta> loginAtleta(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Atleta> atleta = atletaService.loginAtleta(loginRequest.getNomeUsuario(), loginRequest.getSenha());
        if (atleta.isPresent()) {
            return ResponseEntity.ok(atleta.get());
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarAtleta(@Valid @RequestBody Atleta atleta, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        atletaService.registrarAtleta(atleta);
        return ResponseEntity.status(201).body(atleta);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LoginRequest {
        @NotBlank(message = "NomeUsuario é obrigatório")
        @Size(max = 255, message = "NomeUsuario tem que ter no máximo 255 caracteres ")
        private String nomeUsuario;

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, max = 255, message = "Senha deve ter pelo menos 6 caracteres  e máximo de 255")
        private String senha;
    }
}
