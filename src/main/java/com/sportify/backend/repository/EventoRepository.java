package com.sportify.backend.repository;

import com.sportify.backend.model.Atleta;
import java.util.List;

public interface EventoRepository {

    List<Atleta> listarAtletasPeloPatrocinador(String patrocinadorCnpj);

    List<Atleta> buscarAtletasPorModalidade(String modalidade);
}
