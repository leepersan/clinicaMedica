package com.iefp.clinicaMedica.controller;

import com.iefp.clinicaMedica.model.Utilizador;
import com.iefp.clinicaMedica.service.ListagemService;
import com.iefp.clinicaMedica.service.RegistoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;


@Controller
public class RegistoController {

    private final RegistoService registoService;
    private final ListagemService listagemService;

    public RegistoController(RegistoService registoService, ListagemService listagemService) {
        this.registoService = registoService;
        this.listagemService = listagemService;
    }

    //Registar utilizador
    @GetMapping("/registar")
    public String mostrarFormularioRegisto(HttpSession session, Model model) {
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
                                     Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA")) {
            return "redirect:/home";
        }

        try {
            if (perfil.equals("PACIENTE")) {
                registoService.registarPaciente(
                        nome,
                        email,
                        senha,
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


    @PostMapping("/disponibilidades")
    public String criarDisponibilidades(@RequestParam Long medicoId,
                                        @RequestParam LocalDate data,
                                        @RequestParam LocalTime horaInicioTrabalho,
                                        @RequestParam LocalTime horaFimTrabalho,
                                        Model model, HttpSession session) {
        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("MEDICO")) {
            return "redirect:/home";
        }


        try {
            registoService.criarDisponibilidade(
                    medicoId,
                    data,
                    horaInicioTrabalho,
                    horaFimTrabalho
            );

            return "redirect:/disponibilidades";

        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("disponibilidades", listagemService.listarTodasDisponibilidades());
            model.addAttribute("medicos", listagemService.listarMedicos());
            return "disponibilidades";

        }
    }

    @PostMapping("/consultas")
    public String marcarConsulta(@RequestParam Long disponibilidadeId
            ,
                                 @RequestParam Long pacienteId,
                                 @RequestParam(required = false) Long secretariaId,
                                 Model model, HttpSession session) {

        Utilizador utilizadorLogado = (Utilizador) session.getAttribute("utilizadorLogado");

        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        if (!utilizadorLogado.getPerfil().equals("SECRETARIA") &&
        !utilizadorLogado.getPerfil().equals("PACIENTE")){
            return "redirect:/home";
        }
        try {
            registoService.marcarConsulta(
                    disponibilidadeId
                    ,
                    pacienteId,
                    secretariaId
            );

            return "redirect:/consultas";

        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("consultas", listagemService.listarTodasConsultas());
            model.addAttribute("especialidades", listagemService.listarEspecialidades());
            model.addAttribute("pacientes", listagemService.listarPacientes());
            model.addAttribute("secretarias", listagemService.listarSecretarias());
            return "consultas";
        }
    }

    @PostMapping("/receitas")
    public String criarReceita(@RequestParam Long consultaId,
                               @RequestParam String medicamento,
                               @RequestParam String dosagem,
                               @RequestParam String instrucoes,
                               Model model) {

        try {

            registoService.criarReceita(
                    consultaId,
                    medicamento,
                    dosagem,
                    instrucoes
            );
            return "redirect:/receitas";

        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("receitas", listagemService.listarReceitas());
            model.addAttribute("consultas", listagemService.listarTodasConsultas());

            return "receitas";
        }


    }

    @PostMapping("/exames")
    public String criarExame(@RequestParam Long consultaId,
                             @RequestParam String tipoExame,
                             @RequestParam String descricao,
                             @RequestParam(required = false) String resultado,
                             Model model) {

        try {
            registoService.criarExame(
                    consultaId,
                    tipoExame,
                    descricao,
                    resultado
            );
            return "redirect:/exames";

        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("exames", listagemService.listarExames());
            model.addAttribute("consultas", listagemService.listarTodasConsultas());

            return "exames";
        }
    }
}