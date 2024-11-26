package com.sportify.backend.repository;

import com.sportify.backend.model.Atleta;
import java.util.List;
import java.util.Optional;

public interface AtletaRepository {

    List<AtletaHabilidadeCount> listarAtletasComMultiplasHabilidades();

    List<Atleta> acharAtletasPeloEsporte(String esporte);

    Optional<Atleta> login(String nomeUsuario, String senha);

    void salvar(Atleta atleta);

    class AtletaHabilidadeCount {
        private String nome;
        private int quantidadeHabilidades;

        // Getters and Setters

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getQuantidadeHabilidades() {
            return quantidadeHabilidades;
        }

        public void setQuantidadeHabilidades(int quantidadeHabilidades) {
            this.quantidadeHabilidades = quantidadeHabilidades;
        }
    }
}
