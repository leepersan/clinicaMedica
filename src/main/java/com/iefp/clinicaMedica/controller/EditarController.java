package com.iefp.clinicaMedica.controller;


import com.iefp.clinicaMedica.model.Medico;
import com.iefp.clinicaMedica.model.Paciente;
import com.iefp.clinicaMedica.model.Secretaria;
import com.iefp.clinicaMedica.service.EditarService;
import com.iefp.clinicaMedica.service.ListagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class EditarController {

    private final EditarService editarService;
    private final ListagemService listagemService;

    public EditarController(EditarService editarService, ListagemService listagemService) {
        this.editarService = editarService;
        this.listagemService = listagemService;
    }

    //Editar Paciente

    @GetMapping("/editar/paciente/{id}")

    public String mostrarEditarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = listagemService.procurarPacienteporId(id);

        model.addAttribute("item", paciente);
        model.addAttribute("tipo", "PACIENTE");

        return "editar-utilizador";

    }

    @GetMapping("/editar/medico/{id}")
    public String mostrarEditarMedico(@PathVariable Long id, Model model) {
        Medico medico = listagemService.procurarMedicoporId(id);

        model.addAttribute("item", medico);
        model.addAttribute("tipo", "MEDICO");

        return "editar-utilizador";
    }

    @GetMapping("/editar/secretaria/{id}")
    public String mostrarEditarSecretaria(@PathVariable Long id, Model model) {
        Secretaria secretaria = listagemService.procurarSecretariaporId(id);

        model.addAttribute("item", secretaria);
        model.addAttribute("tipo", "SECRETARIA");

        return "editar-utilizador";

    }

    // Editar no formulário o paciente

    @PostMapping("/editar/paciente/{id}")

    public String editarPaciente(@PathVariable Long id,
                                 @RequestParam String nome,
                                 @RequestParam String email,
                                 @RequestParam String senha,
                                 @RequestParam LocalDate dataNascimento,
                                 @RequestParam String telefone,
                                 @RequestParam String endereco) {

        editarService.editarPaciente(
                id,
                nome,
                email,
                senha,
                dataNascimento,
                telefone,
                endereco
        );
        return "redirect:/pacientes";
    }

    // Editar no formulário o médico

    @PostMapping("/editar/medico/{id}")

    public String editarMedico(@PathVariable Long id,
                               @RequestParam String nome,
                               @RequestParam String email,
                               @RequestParam String senha,
                               @RequestParam LocalDate dataNascimento,
                               @RequestParam String telefone,
                               @RequestParam String endereco,
                               @RequestParam String especialidade) {

        editarService.editarMedico(
                id,
                nome,
                email,
                senha,
                dataNascimento,
                telefone,
                endereco,
                especialidade
        );
        return "redirect:/medicos";

    }

    // Editar no formulário a secretária

    @PostMapping("/editar/secretaria/{id}")

    public String editarSecretaria(@PathVariable Long id,
                                   @RequestParam String nome,
                                   @RequestParam String email,
                                   @RequestParam String senha,
                                   @RequestParam LocalDate dataNascimento,
                                   @RequestParam String telefone,
                                   @RequestParam String endereco) {

        editarService.editarSecretaria(
                id,
                nome,
                email,
                senha,
                dataNascimento,
                telefone,
                endereco
        );
        return "redirect:/secretarias";
    }

}
