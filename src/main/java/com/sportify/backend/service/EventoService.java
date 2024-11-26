package com.sportify.backend.service;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Atleta> getAtletasPeloPatrocinador(String patrocinadorCnpj) {
        return eventoRepository.listarAtletasPeloPatrocinador(patrocinadorCnpj);
    }

    public List<Atleta> getAtletasPorModalidade(String modalidade) {
        return eventoRepository.buscarAtletasPorModalidade(modalidade);
    }
}
