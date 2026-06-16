package com.Murilo.SonicLista.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Murilo.SonicLista.model.Jogo;
import com.Murilo.SonicLista.model.JogoService;
import com.Murilo.SonicLista.model.VideoGame;
import com.Murilo.SonicLista.model.VideoGameService;

@Controller
public class MenuController {

    @Autowired
    JogoService jogoService;

    @Autowired
    VideoGameService videoGameService;

    @Autowired
    private com.Murilo.SonicLista.service.CloudinaryService cloudinaryService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String paginaAdmin(Model model){
        List<Jogo> listaDeJogos = jogoService.listarJogos();
        model.addAttribute("jogos", listaDeJogos);
        return "admin";
    }

    @GetMapping("/cadastroJogo")
    public String formJogo(Model model) {
        model.addAttribute("jogo", new Jogo());
        return "form-jogo";
    }

    @PostMapping("/cadastroJogo")
    public String postJogo(@ModelAttribute Jogo jogo, @RequestParam(value = "file", required = false) MultipartFile file) {
        
        if (jogo.getId() != null && !jogo.getId().toString().isEmpty()) {
            Jogo jogoAntigo = jogoService.buscarJogoPorId(jogo.getId().toString());
            
            if (jogoAntigo != null) {
                jogo.setVideoGames(jogoAntigo.getVideoGames());
                
                if (file == null || file.isEmpty()) {
                    jogo.setUrlImagem(jogoAntigo.getUrlImagem());
                }
            }
        }

        if (file != null && !file.isEmpty()) {
            String linkCloudinary = cloudinaryService.fazerUpload(file);
            
            if (linkCloudinary != null) {
                jogo.setUrlImagem(linkCloudinary);
            }
        }

        if (jogo.getId() == null || jogo.getId().toString().isEmpty()) {
            jogoService.inserirJogo(jogo);
        } else {
            jogoService.atualizarJogo(jogo);
        }
        return "redirect:/admin"; 
    }
    
    @GetMapping("/editar/{id}")
    public String formEditarJogo(@PathVariable String id, Model model) {
        Jogo jogoEncontrado = jogoService.buscarJogoPorId(id);
        model.addAttribute("jogo", jogoEncontrado);
        return "form-jogo";
    }

    @PostMapping("/excluir/{id}")
    public String excluirJogo(@PathVariable("id") String id) {
        jogoService.excluirJogo(id);
        return "redirect:/admin";
    }

    @GetMapping("/videogame/atribuir")
    public String formAtribuir(Model model) {
        model.addAttribute("jogos", jogoService.listarJogos());
        model.addAttribute("videoGames", videoGameService.listar());
        return "atribuir-videogame"; 
    }

    @PostMapping("/videogame/atribuir")
    public String postAtribuir(@RequestParam String jogoId, @RequestParam UUID videoGameId) {
        Jogo jogo = jogoService.buscarJogoPorId(jogoId);
        VideoGame videoGame = videoGameService.buscar(videoGameId);

        if (jogo != null && videoGame != null) {
            if (jogo.getVideoGames() == null) {
                jogo.setVideoGames(new ArrayList<>());
            }

            if (!jogo.getVideoGames().contains(videoGame)) {
                jogo.getVideoGames().add(videoGame);
                jogoService.atualizarJogo(jogo);
            }
        }
        
        return "redirect:/admin";
    }

    @GetMapping("/")
    public String paginaPrincipal(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "era", required = false) String era,
            Model model) {
        
        List<Jogo> listaDeJogos = new ArrayList<>();

        if (nome != null && !nome.trim().isEmpty()) {
            listaDeJogos = jogoService.pesquisarJogos(nome);
        } 
        else if (era != null && !era.isEmpty()) {
            switch (era) {
                case "classica":
                    listaDeJogos = jogoService.pesquisarPorEra(1991, 1997);
                    break;
                case "aventura":
                    listaDeJogos = jogoService.pesquisarPorEra(1998, 2008);
                    break;
                case "moderna":
                    listaDeJogos = jogoService.pesquisarPorEra(2009, 2026); 
                    break;
            }
        }

        model.addAttribute("jogos", listaDeJogos);
        return "index";
    }
}