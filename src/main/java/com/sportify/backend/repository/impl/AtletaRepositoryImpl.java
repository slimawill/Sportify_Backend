package com.sportify.backend.repository.impl;

import com.sportify.backend.model.Atleta;
import com.sportify.backend.repository.AtletaRepository;
import com.sportify.backend.repository.AtletaRepository.AtletaHabilidadeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AtletaRepositoryImpl implements AtletaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AtletaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class AtletaRowMapper implements RowMapper<Atleta> {
        @Override
        public Atleta mapRow(ResultSet rs, int rowNum) throws SQLException {
            Atleta atleta = new Atleta();
            atleta.setNomeUsuario(rs.getString("NOME_USUARIO"));
            atleta.setSenha(rs.getString("SENHA"));
            atleta.setNome(rs.getString("NOME"));
            atleta.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
            atleta.setEsporte(rs.getString("ESPORTE"));
            atleta.setGenero(rs.getString("GENERO").charAt(0));
            atleta.setAgenciaCnpj(rs.getString("AGENCIA_CNPJ"));
            atleta.setEquipeCnpj(rs.getString("EQUIPE_CNPJ"));
            return atleta;
        }
    }

    private static class AtletaHabilidadeCountRowMapper implements RowMapper<AtletaHabilidadeCount> {
        @Override
        public AtletaHabilidadeCount mapRow(ResultSet rs, int rowNum) throws SQLException {
            AtletaHabilidadeCount count = new AtletaHabilidadeCount();
            count.setNome(rs.getString("NOME"));
            count.setQuantidadeHabilidades(rs.getInt("QuantidadeHabilidades"));
            return count;
        }
    }

    @Override
    public List<AtletaHabilidadeCount> listarAtletasComMultiplasHabilidades() {
        String sql = "SELECT A.NOME, COUNT(H.DESCRICAO) AS QuantidadeHabilidades " +
                "FROM SPF_ATLETA A " +
                "LEFT JOIN SPF_HABILIDADE H ON A.NOME_USUARIO = H.NOME_USUARIO " +
                "GROUP BY A.NOME " +
                "HAVING COUNT(H.DESCRICAO) > 1";
        return jdbcTemplate.query(sql, new AtletaHabilidadeCountRowMapper());
    }

    @Override
    public List<Atleta> acharAtletasPeloEsporte(String esporte) {
        String sql = "SELECT * FROM SPF_ATLETA WHERE ESPORTE = ?";
        return jdbcTemplate.query(sql, new AtletaRowMapper(), esporte);
    }

    @Override
    public Optional<Atleta> login(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM SPF_ATLETA WHERE NOME_USUARIO = ? AND SENHA = ?";
        List<Atleta> atletas = jdbcTemplate.query(sql, new AtletaRowMapper(), nomeUsuario, senha);
        return atletas.stream().findFirst();
    }

    @Override
    public void salvar(Atleta atleta) {
        String sql = "INSERT INTO SPF_ATLETA (NOME_USUARIO, NOME, DATA_NASCIMENTO, ESPORTE, GENERO, AGENCIA_CNPJ, EQUIPE_CNPJ, SENHA) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                atleta.getNomeUsuario(),
                atleta.getNome(),
                atleta.getDataNascimento(),
                atleta.getEsporte(),
                String.valueOf(atleta.getGenero()),
                atleta.getAgenciaCnpj(),
                atleta.getEquipeCnpj(),
                atleta.getSenha());
    }
}
