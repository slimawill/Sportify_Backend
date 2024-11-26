package com.sportify.backend.service;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.repository.AtletaRepository;
import com.sportify.backend.repository.AtletaRepository.AtletaHabilidadeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtletaService {

    private final AtletaRepository atletaRepository;

    @Autowired
    public AtletaService(AtletaRepository atletaRepository) {
        this.atletaRepository = atletaRepository;
    }

    public List<AtletaHabilidadeCount> getAtletasComMultiplasHabilidades() {
        return atletaRepository.listarAtletasComMultiplasHabilidades();
    }

    public List<Atleta> getAtletasPeloEsporte(String esporte) {
        return atletaRepository.acharAtletasPeloEsporte(esporte);
    }

    @Transactional
    public void registrarAtleta(Atleta atleta) {
        atletaRepository.salvar(atleta);
    }

    public Optional<Atleta> loginAtleta(String nomeUsuario, String senha) {
        if (nomeUsuario == null || nomeUsuario.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Os campos usuário e senha não devem ser nulos.");
        }
        return atletaRepository.login(nomeUsuario, senha);
    }
}