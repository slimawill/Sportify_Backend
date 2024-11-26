package com.sportify.backend.repository.impl;

import com.sportify.backend.model.Equipe;
import com.sportify.backend.repository.EquipeRepository;
import com.sportify.backend.repository.EquipeRepository.EquipeAthleteCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EquipeRepositoryImpl implements EquipeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EquipeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class EquipeAthleteCountRowMapper implements RowMapper<EquipeAthleteCount> {
        @Override
        public EquipeAthleteCount mapRow(ResultSet rs, int rowNum) throws SQLException {
            EquipeAthleteCount count = new EquipeAthleteCount();
            count.setNomeEquipe(rs.getString("NOME"));
            count.setTotalAtletas(rs.getInt("TotalAtletas"));
            return count;
        }
    }

    @Override
    public List<EquipeAthleteCount> exibirTimesEQuantidadeAtletas() {
        String sql = "SELECT E.NOME, COUNT(A.NOME_USUARIO) AS TotalAtletas " +
                "FROM SPF_EQUIPE E " +
                "LEFT JOIN SPF_ATLETA A ON E.CNPJ = A.EQUIPE_CNPJ " +
                "GROUP BY E.NOME";
        return jdbcTemplate.query(sql, new EquipeAthleteCountRowMapper());
    }
}
