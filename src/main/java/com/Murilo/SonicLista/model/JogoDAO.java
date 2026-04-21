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
        // SQL atualizado
        String sql = "INSERT INTO tb_jogos(nm_jogo, ano_lancamento, url_imagem) VALUES (?,?,?)";
        
        // Agora são 3 posições
        Object[] obj = new Object[3]; 
        obj[0] = jogo.getNmJogo();
        obj[1] = jogo.getAnoLancamento();
        obj[2] = jogo.getUrlImagem(); // Pegando a imagem
        
        jdbc.update(sql, obj);
    }
	public List<Jogo> listarJogos() {
        String sql = "SELECT * FROM tb_jogos";
        
        // O jdbc.query vai à base de dados, pega em todas as linhas e converte para uma Lista de Jogos
        return jdbc.query(sql, (rs, rowNum) -> {
            Jogo jogo = new Jogo();
            // rs (ResultSet) é a linha da base de dados. Vamos extrair as colunas pelo nome
            jogo.setId(rs.getString("id"));
            jogo.setNmJogo(rs.getString("nm_jogo"));
            jogo.setAnoLancamento(rs.getInt("ano_lancamento"));
            jogo.setUrlImagem(rs.getString("url_imagem"));
            
            return jogo;
        });
	}
	public Jogo buscarJogoPorId(String id) {
        // O ::uuid é necessário porque no Java o id é String, mas no Postgres é UUID
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

    // Método para atualizar os dados do jogo que já existe
    public void atualizarJogo(Jogo jogo) {
        String sql = "UPDATE tb_jogos SET nm_jogo=?, ano_lancamento=?, url_imagem=? WHERE id=?::uuid";
        Object[] obj = new Object[4];
        obj[0] = jogo.getNmJogo();
        obj[1] = jogo.getAnoLancamento();
        obj[2] = jogo.getUrlImagem();
        obj[3] = jogo.getId(); // O ID vai por último na condição WHERE
        
        jdbc.update(sql, obj);
    }
}