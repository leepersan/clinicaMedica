package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.model.Utilizador;
import com.iefp.clinicaMedica.service.ListagemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListagemController {
    private final ListagemService listagemService;

    public ListagemController(ListagemService listagemService) {
        this.listagemService = listagemService;
    }

    @GetMapping("/")
    public String paginaInicial() {
        return "redirect:/login";
    }

    //Listar utilizadores
    @GetMapping("/utilizadores")
    public String listarUtilizadores(Model model, HttpSession session) {

        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA")) {
            return "redirect:/home";
        }
        model.addAttribute("lista", listagemService.listarUtilizadores());
        model.addAttribute("tipo", "UTILIZADOR");
        return "listagem";
    }

    //Listar pacientes
    @GetMapping("/pacientes")
    public String listarPacientes(Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA")) {
            return "redirect:/home";
        }
        model.addAttribute("lista", listagemService.listarPacientes());
        model.addAttribute("tipo", "PACIENTE");
        return "listagem";
    }

    //Listar medicos
    @GetMapping("/medicos")
    public String listarMedicos(Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA")) {
            return "redirect:/home";
        }
        model.addAttribute("lista", listagemService.listarMedicos());
        model.addAttribute("tipo", "MEDICO");
        return "listagem";
    }

    //listar secretárias
    @GetMapping("/secretarias")
    public String listarSecretarias(Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA")) {
            return "redirect:/home";
        }
        model.addAttribute("lista", listagemService.listarSecretarias());
        model.addAttribute("tipo", "SECRETARIA");
        return "listagem";
    }

    //listar disponibilidades
    @GetMapping("/disponibilidades")
    public String listarDisponibilidade(Model model, HttpSession session) {

        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("MEDICO")) {
            return "redirect:/home";
        }
        model.addAttribute("disponibilidades", listagemService.listarTodasDisponibilidades());
        model.addAttribute("medicos", listagemService.listarMedicos());
        return "disponibilidades";
    }

    //listar consultas
    @GetMapping("/consultas")
    public String listarConsultas(@RequestParam(required = false) String especialidade,
                                  Model model, HttpSession session) {

        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA") && !utilizadorLogado.getPerfil().equals("PACIENTE")) {
            return "redirect:/home";
        }
        model.addAttribute("consultas", listagemService.listarTodasConsultas());
        model.addAttribute("especialidades", listagemService.listarEspecialidades());
        model.addAttribute("pacientes", listagemService.listarPacientes());
        model.addAttribute("secretarias", listagemService.listarSecretarias());
        model.addAttribute("especialidadeSelecionada", especialidade);

        if (especialidade != null && !especialidade.isEmpty()) {
            model.addAttribute("disponibilidades",
                    listagemService.listarDisponibilidadePorEspecialidade(especialidade));
        }

        return "consultas";
    }


    //listar receitas
    @GetMapping("/receitas")
    public String listarReceitas(Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("MEDICO")) {
            return "redirect:/home";
        }
        model.addAttribute("receitas", listagemService.listarReceitas());
        model.addAttribute("consultas", listagemService.listarTodasConsultas());
        return "receitas";
    }

    //listar exames
    @GetMapping("/exames")
    public String listarExames(Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("MEDICO")) {
            return "redirect:/home";
        }
        model.addAttribute("exames", listagemService.listarExames());
        model.addAttribute("consultas", listagemService.listarTodasConsultas());
        return "exames";
    }
}