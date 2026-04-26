package com.Murilo.SonicLista.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    @Autowired
    JogoDAO jogoDAO;

    public void inserirJogo(Jogo jogo){
        jogoDAO.inserirJogo(jogo);
    }
    public List<Jogo> listarJogos(){
        return jogoDAO.listarJogos();
    }
    public Jogo buscarJogoPorId(String id) {
        return jogoDAO.buscarJogoPorId(id);
    }

    public void atualizarJogo(Jogo jogo) {
        jogoDAO.atualizarJogo(jogo);
    }
    public void excluirJogo(String uuid) {
        jogoDAO.excluirJogo(uuid);
    }
    public List<Jogo> pesquisarJogos(String nome) {
        return jogoDAO.pesquisarJogos(nome);
    }
    public List<Jogo> pesquisarPorEra(int anoInicio, int anoFim) {
        return jogoDAO.pesquisarPorEra(anoInicio, anoFim);
    }
}