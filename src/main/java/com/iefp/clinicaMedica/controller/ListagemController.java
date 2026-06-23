package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.repository.PacienteRepository;
import com.iefp.clinicaMedica.repository.UtilizadorRepository;
import com.iefp.clinicaMedica.service.ListagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListagemController {
    private final ListagemService listagemService;

    public ListagemController(ListagemService listagemService) {
        this.listagemService = listagemService;
    }

    @GetMapping("/")
    public String paginaInicial(){
        return "redirect:/pacientes";
    }
    //Listar utilizadores

    @GetMapping ("/utilizadores")
    public String listarUtilizadores(Model model) {
        model.addAttribute("lista", listagemService.listarUtilizadores());
        model.addAttribute("tipo", "UTILIZADOR");
        return "listagem";

    }
    //Listar pacientes

    @GetMapping ("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("lista", listagemService.listarPacientes());
        model.addAttribute("tipo", "PACIENTE");
        return "listagem";

    }

    //Listar Médicos

    @GetMapping ("/medicos")
    public String listarMedicos(Model model) {
        model.addAttribute("lista", listagemService.listarMedicos());
        model.addAttribute("tipo", "MEDICO");
        return "listagem";
    }

    //Listar Secretárias

    @GetMapping ("/secretarias")
    public String listarSecretarias(Model model) {
        model.addAttribute("lista", listagemService.listarSecretarias());
        model.addAttribute("tipo", "SECRETARIA");
        return "listagem";

    }



}
