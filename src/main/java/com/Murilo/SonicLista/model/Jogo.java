package com.Murilo.SonicLista.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Jogo {

    private String id;
    private String nmJogo;
    private Integer anoLancamento;
    private String urlImagem;

    public Jogo() {
    }


    public Jogo(String nmJogo, Integer anoLancamento, String urlImagem) {
        this.nmJogo = nmJogo;
        this.anoLancamento = anoLancamento;
        this.urlImagem = urlImagem;
    }

    public String getId() {
        return id; 
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNmJogo() {
        return nmJogo; 
    }
    public void setNmJogo(String nmJogo) {
        this.nmJogo = nmJogo;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }


    public static Jogo converter(Map<String,Object> registro){
        String nmJogo = (String) registro.get("nm_jogo");
        UUID id = (UUID) registro.get("id");
        Integer anoLancamento = (Integer) registro.get("ano_lancamento");
        String urlImagem = (String) registro.get("url_imagem");
        
        Jogo jogo = new Jogo(nmJogo, anoLancamento, urlImagem);
        jogo.setId(id.toString());
        return jogo;
    }

    public static ArrayList<Jogo> converterTodos(List<Map<String,Object>> registros){
        ArrayList<Jogo> aux = new ArrayList<>();
        for(Map<String,Object> registro : registros){
            aux.add(converter(registro));
        }
        return aux;
    }
}