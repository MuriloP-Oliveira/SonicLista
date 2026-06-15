package com.Murilo.SonicLista.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nm_jogo", nullable = false)
    private String nmJogo;

    @Column(name = "ano_lancamento", nullable = false)
    private Integer anoLancamento;

    @Column(name = "url_imagem")
    private String urlImagem;

    // Relação N:N com a tabela de video_game
    @ManyToMany
    @JoinTable(
        name = "jogo_video_game",
        joinColumns = @JoinColumn(name = "jogo_id"),
        inverseJoinColumns = @JoinColumn(name = "video_game_id")
    )
    private List<VideoGame> videoGames;

    public Jogo() {
    }

    public Jogo(String nmJogo, Integer anoLancamento, String urlImagem) {
        this.nmJogo = nmJogo;
        this.anoLancamento = anoLancamento;
        this.urlImagem = urlImagem;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNmJogo() { return nmJogo; }
    public void setNmJogo(String nmJogo) { this.nmJogo = nmJogo; }

    public Integer getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(Integer anoLancamento) { this.anoLancamento = anoLancamento; }

    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }

    public List<VideoGame> getVideoGames() { return videoGames; }
    public void setVideoGames(List<VideoGame> videoGames) { this.videoGames = videoGames; }
}
