package com.Murilo.SonicLista.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class JogoDAO {

    @Autowired
    DataSource dataSource;
	
    JdbcTemplate jdbc;
	
    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }
	
	public void inserirJogo(Jogo jogo) {
        String sql = "INSERT INTO tb_jogos(nm_jogo, ano_lancamento, url_imagem) VALUES (?,?,?)";
        Object[] obj = new Object[3]; 
        obj[0] = jogo.getNmJogo();
        obj[1] = jogo.getAnoLancamento();
        obj[2] = jogo.getUrlImagem(); 
        jdbc.update(sql, obj);
    }

	public List<Jogo> listarJogos() {
        String sql = "SELECT * FROM tb_jogos";
        return jdbc.query(sql, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            jogo.setId(rs.getString("id"));
            jogo.setNmJogo(rs.getString("nm_jogo"));
            jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
            jogo.setUrlImagem(rs.getString("url_imagem"));
            return jogo;
        });
	}

	public Jogo buscarJogoPorId(String id) {
        String sql = "SELECT * FROM tb_jogos WHERE id = ?::uuid"; 
        return jdbc.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            jogo.setId(rs.getString("id"));
            jogo.setNmJogo(rs.getString("nm_jogo"));
            jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
            jogo.setUrlImagem(rs.getString("url_imagem"));
            return jogo;
        });
    }

    public void atualizarJogo(Jogo jogo) {
        String sql = "UPDATE tb_jogos SET nm_jogo=?, ano_lancamento=?, url_imagem=? WHERE id=?::uuid";
        Object[] obj = new Object[4];
        obj[0] = jogo.getNmJogo();
        obj[1] = jogo.getAnoLancamento();
        obj[2] = jogo.getUrlImagem();
        obj[3] = jogo.getId(); 
        jdbc.update(sql, obj);
    }

    public void excluirJogo(String uuid) {
        String sql = "DELETE FROM tb_jogos WHERE id=?::uuid";
        jdbc.update(sql, uuid);
    }

    public List<Jogo> pesquisarJogos(String nome) {
        String sql = "SELECT * FROM tb_jogos WHERE nm_jogo ILIKE ?";
        String termoBusca = "%" + nome + "%";
        
        return jdbc.query(sql, new Object[]{termoBusca}, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            jogo.setId(rs.getString("id"));
            jogo.setNmJogo(rs.getString("nm_jogo"));
            jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
            jogo.setUrlImagem(rs.getString("url_imagem"));
            return jogo;
        });
    }
    public List<Jogo> pesquisarPorEra(int anoInicio, int anoFim) {
        String sql = "SELECT * FROM tb_jogos WHERE ano_lancamento BETWEEN ? AND ? ORDER BY ano_lancamento ASC";
        
        return jdbc.query(sql, new Object[]{anoInicio, anoFim}, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            jogo.setId(rs.getString("id"));
            jogo.setNmJogo(rs.getString("nm_jogo"));
            jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
            jogo.setUrlImagem(rs.getString("url_imagem"));
            return jogo;
        });
    }
}