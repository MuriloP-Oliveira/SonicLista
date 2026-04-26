package com.Murilo.SonicLista.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Murilo.SonicLista.model.Jogo;
import com.Murilo.SonicLista.model.JogoService;

@Controller
public class MenuController {

    @Autowired
    JogoService jogoService;

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
    public String postJogo(@ModelAttribute Jogo jogo) {
        if (jogo.getId() == null || jogo.getId().isEmpty()) {
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

    @GetMapping("/excluir/{id}")
    public String excluirJogo(@PathVariable String id) {
        // Agora usa o jogoService diretamente
        jogoService.excluirJogo(id);
        return "redirect:/admin";
    }

    @GetMapping("/")
    public String paginaPrincipal(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "era", required = false) String era,
            Model model) {
        
        List<Jogo> listaDeJogos = new ArrayList<>();

        // Prioridade 1: Busca por Nome
        if (nome != null && !nome.trim().isEmpty()) {
            listaDeJogos = jogoService.pesquisarJogos(nome);
        } 
        // Prioridade 2: Busca por Era
        else if (era != null && !era.isEmpty()) {
            switch (era) {
                case "classica":
                    listaDeJogos = jogoService.pesquisarPorEra(1991, 1997);
                    break;
                case "aventura":
                    listaDeJogos = jogoService.pesquisarPorEra(1998, 2008);
                    break;
                case "moderna":
                    listaDeJogos = jogoService.pesquisarPorEra(2009, 2026); // 2026 para cobrir o futuro próximo
                    break;
            }
        }

        model.addAttribute("jogos", listaDeJogos);
        return "index";
    }
}
