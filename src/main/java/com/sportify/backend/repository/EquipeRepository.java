package com.sportify.backend.repository;

import com.sportify.backend.model.Equipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface EquipeRepository {

    List<EquipeAthleteCount> exibirTimesEQuantidadeAtletas();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class EquipeAthleteCount {
        private String nomeEquipe;
        private int totalAtletas;
    }
}
