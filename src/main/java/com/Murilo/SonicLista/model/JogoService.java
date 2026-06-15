package com.Murilo.SonicLista.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repo;

    public void inserirJogo(Jogo jogo){
        repo.save(jogo);
    }
    public List<Jogo> listarJogos(){
        return repo.findAll();
    }
    public Jogo buscarJogoPorId(String id) {
        return repo.findById(UUID.fromString(id)).orElse(null);
    }

    public void atualizarJogo(Jogo jogo) {
        repo.save(jogo); 
    }
    
    public void excluirJogo(String id) {
        repo.deleteById(UUID.fromString(id));
    }
    public List<Jogo> pesquisarJogos(String nome) {
        return repo.findByNmJogoContainingIgnoreCase(nome);
    }
    public List<Jogo> pesquisarPorEra(int anoInicio, int anoFim) {
        return repo.findByAnoLancamentoBetweenOrderByAnoLancamentoAsc(anoInicio, anoFim);
    }
}