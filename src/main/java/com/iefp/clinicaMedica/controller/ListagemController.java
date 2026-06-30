package com.iefp.clinicaMedica.controller;


import com.iefp.clinicaMedica.service.ListagemService;
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
        return "redirect:/pacientes";
    }

    //Listar utilizadores
    @GetMapping("/utilizadores")
    public String listarUtilizadores(Model model) {
        model.addAttribute("lista", listagemService.listarUtilizadores());
        model.addAttribute("tipo", "UTILIZADOR");
        return "listagem";
    }

    //Listar pacientes
    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("lista", listagemService.listarPacientes());
        model.addAttribute("tipo", "PACIENTE");
        return "listagem";
    }

    //Listar medicos
    @GetMapping("/medicos")
    public String listarMedicos(Model model) {
        model.addAttribute("lista", listagemService.listarMedicos());
        model.addAttribute("tipo", "MEDICO");
        return "listagem";
    }

    //listar secretárias
    @GetMapping("/secretarias")
    public String listarSecretarias(Model model) {
        model.addAttribute("lista", listagemService.listarSecretarias());
        model.addAttribute("tipo", "SECRETARIA");
        return "listagem";
    }

    //listar disponibilidades
    @GetMapping("/disponibilidades")
    public String listarDisponibilidade(Model model) {

        model.addAttribute("disponibilidades", listagemService.listarTodasDisponibilidades());
        model.addAttribute("medicos", listagemService.listarMedicos());
        return "disponibilidades";
    }

    //listar consultas
    @GetMapping("/consultas")
    public String listarConsultas(@RequestParam(required = false) String especialidade,
                                  Model model) {
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

    //Listar Receitas

    @GetMapping("/receitas")
    public String listarReceitas(Model model) {
        model.addAttribute("receitas", listagemService.listarReceitas());
        model.addAttribute("consultas", listagemService.listarTodasConsultas());
        return "receitas";
    }

    //Listar Exames

    @GetMapping("/exames")
    public String listarExames(Model model) {
        model.addAttribute("exames", listagemService.listarExames());
        model.addAttribute("consultas", listagemService.listarTodasConsultas());
        return "exames";
    }

}