package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.service.RemoverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RemoverController {

    private final RemoverService removerService;

    public RemoverController(RemoverService removerService) {
        this.removerService = removerService;
    }

    //Remover Paciente
    @GetMapping("remover/paciente/{id}")

    public String removerPaciente(@PathVariable Long id) {
        removerService.removerPaciente(id);
        return "redirect:/pacientes";

    }

    //Remover Médico
    @GetMapping("remover/medico/{id}")

    public String removerMedico(@PathVariable Long id) {
        removerService.removerMedico(id);
        return "redirect:/medicos";

    }

    //Remover Secretarias
    @GetMapping("remover/secretaria/{id}")

    public String removerSecretaria(@PathVariable Long id) {
        removerService.removerSecretaria(id);
        return "redirect:/secretarias";

    }
}
