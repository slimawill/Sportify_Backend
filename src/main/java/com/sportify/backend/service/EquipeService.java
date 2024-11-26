package com.sportify.backend.service;

import com.sportify.backend.repository.EquipeRepository;
import com.sportify.backend.repository.EquipeRepository.EquipeAthleteCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public List<EquipeAthleteCount> getTmesEQuantidadeAtletas() {
        return equipeRepository.exibirTimesEQuantidadeAtletas();
    }
}
