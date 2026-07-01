package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.model.Utilizador;
import com.iefp.clinicaMedica.service.ListagemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    private final ListagemService listagemService;

    public LoginController(ListagemService listagemService) {
        this.listagemService = listagemService;
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email,
                             @RequestParam String senha,
                             HttpSession session,
                             Model model){

        Optional<Utilizador> utilizadorEncontrado = listagemService.procurarPorEmailSenha(email, senha);

        if(utilizadorEncontrado.isPresent()){
            Utilizador utilizador = utilizadorEncontrado.get();

            session.setAttribute("utilizadorLogado", utilizador);


            return "redirect:/home";
        }
        model.addAttribute("erro", "Email ou senha inválidos");
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        Utilizador utilizadorLogado =
                (Utilizador) session.getAttribute("utilizadorLogado");

        if(utilizadorLogado == null){
            return "redirect:/login";
        }
        model.addAttribute("utilizador", utilizadorLogado);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}
