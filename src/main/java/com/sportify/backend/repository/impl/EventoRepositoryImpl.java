package com.sportify.backend.repository.impl;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventoRepositoryImpl implements EventoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class AtletaRowMapper implements RowMapper<Atleta> {
        @Override
        public Atleta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Atleta atleta = new Atleta();
            atleta.setNomeUsuario(rs.getString("NOME_USUARIO"));
            atleta.setNome(rs.getString("NOME"));
            atleta.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
            atleta.setEsporte(rs.getString("ESPORTE"));
            atleta.setGenero(rs.getString("GENERO").charAt(0));
            atleta.setAgenciaCnpj(rs.getString("AGENCIA_CNPJ"));
            atleta.setEquipeCnpj(rs.getString("EQUIPE_CNPJ"));
            return atleta;
        }
    }

    @Override
    public List<Atleta> listarAtletasPeloPatrocinador(String patrocinadorCnpj) {
        String sql = "SELECT A.* " +
                "FROM SPF_ATLETA A " +
                "JOIN SPF_PARTICIPACAO_INDIVIDUAL PI ON A.NOME_USUARIO = PI.NOME_USUARIO " +
                "JOIN SPF_EVENTO E ON PI.EVENTO_ID = E.ID " +
                "WHERE E.PATROCINADOR_CNPJ = ?";
        return jdbcTemplate.query(sql, new AtletaRowMapper(), patrocinadorCnpj);
    }

    @Override
    public List<Atleta> buscarAtletasPorModalidade(String modalidade) {
        String sql = "SELECT A.* " +
                "FROM SPF_ATLETA A " +
                "WHERE NOT EXISTS ( " +
                "    SELECT E.ID " +
                "    FROM SPF_EVENTO E " +
                "    WHERE E.MODALIDADE = ? " +
                "    MINUS " +
                "    SELECT PI.EVENTO_ID " +
                "    FROM SPF_PARTICIPACAO_INDIVIDUAL PI " +
                "    WHERE PI.NOME_USUARIO = A.NOME_USUARIO " +
                "    AND PI.MODALIDADE = ? " +
                ")";
        return jdbcTemplate.query(sql, new AtletaRowMapper(), modalidade, modalidade);
    }
}
