package com.Murilo.SonicLista.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Murilo.SonicLista.model.VideoGame;
import com.Murilo.SonicLista.model.VideoGameService;

@Controller
@RequestMapping("/videogame")
public class VideoGameController {

    @Autowired
    private VideoGameService videoGameService;

    @GetMapping("/cadastrar")
    public String formVideoGame(Model model) {
        model.addAttribute("videoGame", new VideoGame());
        return "form-videogame";
    }

    @PostMapping("/cadastrar")
    public String postVideoGame(@ModelAttribute VideoGame videoGame) {
        videoGameService.inserir(videoGame);
        return "redirect:/videogame/listar";
    }

    @GetMapping("/listar")
    public String listarVideoGames(Model model) {
        model.addAttribute("videoGames", videoGameService.listar());
        return "listar-videogames";
    }

    @PostMapping("/{id}/deletar")
    public String deletarVideoGame(@PathVariable("id") UUID id) {
        videoGameService.deletar(id);
        return "redirect:/videogame/listar";
    }
}