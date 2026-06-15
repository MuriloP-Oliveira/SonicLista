package com.Murilo.SonicLista.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, UUID> {
    
    // O Spring gera automaticamente o SQL: SELECT * FROM tb_jogos WHERE nm_jogo ILIKE %nome%
    List<Jogo> findByNmJogoContainingIgnoreCase(String nome);
    
    // O Spring gera automaticamente o SQL: SELECT * FROM tb_jogos WHERE ano_lancamento BETWEEN ? AND ? ORDER BY ano_lancamento ASC
    List<Jogo> findByAnoLancamentoBetweenOrderByAnoLancamentoAsc(int anoInicio, int anoFim);
}