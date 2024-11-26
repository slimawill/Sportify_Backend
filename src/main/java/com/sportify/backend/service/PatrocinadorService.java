package com.sportify.backend.service;

import com.sportify.backend.model.Patrocinador;
import com.sportify.backend.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrocinadorService {

    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> getSponsorsOfAllEventTypes() {
        return patrocinadorRepository.findPatrocinadoresSponsoringAllEventTypes();
    }
}
