package com.Murilo.SonicLista.model;

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
}
