package com.Murilo.SonicLista.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    @Autowired
    JogoDAO jogoDAO;

    public void inserirJogo(Jogo jogo){
        jogoDAO.inserirJogo(jogo);
    }

    public ArrayList<Jogo> listarJogos(){
        return jogoDAO.listarJogos();
    }

    public Jogo mostrarJogo(String uuid){
        return jogoDAO.mostrarJogo(uuid);
    }
}