package com.Murilo.SonicLista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Murilo.SonicLista.model.Jogo;
import com.Murilo.SonicLista.model.JogoService;

@Controller
public class MenuController {

    @Autowired
    JogoService jogoService;

    @GetMapping("/")
    public String paginaPrincipal(Model model){
        // Busca todos os jogos salvos no banco
        List<Jogo> listaDeJogos = jogoService.listarJogos();
        
        // Envia para o index.html com o nome "jogos"
        model.addAttribute("jogos", listaDeJogos);
        
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String paginaAdmin(Model model){
        // 1. Vai buscar a lista completa à base de dados
        List<Jogo> listaDeJogos = jogoService.listarJogos();
        
        // 2. Envia a lista para o HTML com o nome "jogos"
        model.addAttribute("jogos", listaDeJogos);
        
        // 3. Abre a página admin.html
        return "admin";
    }

    // Rota que prepara a tela de cadastro enviando um objeto vazio
    @GetMapping("/cadastroJogo")
    public String formJogo(Model model) {
        model.addAttribute("jogo", new Jogo());
        return "form-jogo";
    }
	
    // Rota que recebe os dados do HTML e salva no banco
    @PostMapping("/cadastroJogo")
    public String postJogo(@ModelAttribute Jogo jogo) {
        // Se o ID estiver vazio, é um jogo novo (INSERT)
        if (jogo.getId() == null || jogo.getId().isEmpty()) {
            jogoService.inserirJogo(jogo);
        } 
        // Se já tiver ID, é uma edição (UPDATE)
        else {
            jogoService.atualizarJogo(jogo);
        }
        return "redirect:/admin"; 
    }
    
    @GetMapping("/editar/{id}")
    public String formEditarJogo(@PathVariable String id, Model model) {
        // Busca o jogo no banco
        Jogo jogoEncontrado = jogoService.buscarJogoPorId(id);
        
        // Envia o jogo preenchido para o formulário
        model.addAttribute("jogo", jogoEncontrado);
        return "form-jogo"; // Reutilizamos a mesma tela HTML!
    }

}
