package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.service.RegistoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Controller
public class RegistoController {

    private final RegistoService registoService;

    public RegistoController(RegistoService registoService) {
        this.registoService = registoService;
    }

    //Registar utilizador
    @GetMapping("/registar")
    public String mostrarFormularioRegisto(){

        return "registar-utilizador";
    }

    @PostMapping("/registar")
    public String registarUtilizador(@RequestParam String nome,
                                     @RequestParam String email,
                                     @RequestParam String senha,
                                     @RequestParam String perfil,
                                     @RequestParam LocalDate dataNascimento,
                                     @RequestParam String telefone,
                                     @RequestParam String endereco,
                                     @RequestParam(required = false) String especialidade,
                                     Model model) {
        try {
            if (perfil.equals("PACIENTE")) {
                registoService.registarPaciente(
                        nome,
                        email,
                        senha,
                        perfil,
                        dataNascimento,
                        telefone,
                        endereco
                );

                return "redirect:/pacientes";
            }

            //registar Médico
            if (perfil.equals("MEDICO")) {

                registoService.registarMedico(
                        nome,
                        email,
                        senha,
                        perfil,
                        dataNascimento,
                        telefone,
                        endereco,
                        especialidade
                );
                return "redirect:/medicos";
            }

            //Registar secretária

            if (perfil.equals("SECRETARIA")) {
                registoService.registarSecretaria(
                        nome,
                        email,
                        senha,
                        perfil,
                        dataNascimento,
                        telefone,
                        endereco
                );

                return "redirect:/secretarias";
            }
            return "redirect:/utilizadores";
        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            return "registar-utilizador";
        }
    }
}
