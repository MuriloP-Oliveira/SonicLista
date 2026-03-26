package com.Murilo.SonicLista.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Murilo.SonicLista.model.Jogo;
import com.Murilo.SonicLista.model.JogoService;

@Controller
public class MenuController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String paginaPrincipal(Model model){
        JogoService js = context.getBean(JogoService.class);
        ArrayList<Jogo> listaDeJogos = js.listarJogos();
        model.addAttribute("jogos", listaDeJogos);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String paginaAdmin(Model model){
        JogoService js = context.getBean(JogoService.class);
        ArrayList<Jogo> listaDeJogos = js.listarJogos();
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
        JogoService js = context.getBean(JogoService.class);
        js.inserirJogo(jogo);
        return "redirect:/admin"; 
        
    }

}