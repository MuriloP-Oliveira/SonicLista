package com.Murilo.SonicLista.model;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository repo;

    public VideoGame inserir(VideoGame vg) {
        return repo.save(vg);
    }

    public List<VideoGame> listar() {
        return repo.findAll();
    }

    public VideoGame buscar(UUID id) {
        return repo.findById(id).orElse(null);
    }

    public void deletar(UUID id) {
        repo.deleteById(id);
    }
}