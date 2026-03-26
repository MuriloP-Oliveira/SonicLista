package com.Murilo.SonicLista.model;

import java.util.ArrayList;

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

    public ArrayList<Jogo> listarJogos(){
        String sql = "SELECT * FROM tb_jogos";
        return Jogo.converterTodos(jdbc.queryForList(sql));
    }

    public Jogo mostrarJogo(String uuid){
        String sql = "SELECT * FROM tb_jogos WHERE id=?::uuid";
        return Jogo.converter(jdbc.queryForMap(sql, uuid));
    }

}