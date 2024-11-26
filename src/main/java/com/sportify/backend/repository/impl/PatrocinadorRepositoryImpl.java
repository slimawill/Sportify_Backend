package com.sportify.backend.repository.impl;

import com.sportify.backend.model.Patrocinador;
import com.sportify.backend.repository.PatrocinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatrocinadorRepositoryImpl implements PatrocinadorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatrocinadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class PatrocinadorRowMapper implements RowMapper<Patrocinador> {
        @Override
        public Patrocinador mapRow(ResultSet rs, int rowNum) throws SQLException {
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setCnpj(rs.getString("CNPJ"));
            patrocinador.setEsferaDePoder(rs.getString("ESFERA_DE_PODER"));
            return patrocinador;
        }
    }

    @Override
    public List<Patrocinador> findPatrocinadoresSponsoringAllEventTypes() {
        String sql = "SELECT P.* " +
                "FROM SPF_PATROCINADOR P " +
                "WHERE NOT EXISTS ( " +
                "    SELECT T.TIPO " +
                "    FROM SPF_TIPO_EVENTO T " +
                "    MINUS " +
                "    SELECT E.TIPO " +
                "    FROM SPF_EVENTO E " +
                "    WHERE E.PATROCINADOR_CNPJ = P.CNPJ " +
                ")";
        return jdbcTemplate.query(sql, new PatrocinadorRowMapper());
    }
}
