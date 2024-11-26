package com.sportify.backend.repository;

import com.sportify.backend.model.Patrocinador;
import java.util.List;

public interface PatrocinadorRepository {

    List<Patrocinador> findPatrocinadoresSponsoringAllEventTypes();
}
