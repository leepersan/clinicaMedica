package com.iefp.clinicaMedica.service;

import com.iefp.clinicaMedica.model.Medico;
import com.iefp.clinicaMedica.model.Paciente;
import com.iefp.clinicaMedica.model.Secretaria;
import com.iefp.clinicaMedica.model.Utilizador;
import com.iefp.clinicaMedica.repository.MedicoRepository;
import com.iefp.clinicaMedica.repository.PacienteRepository;
import com.iefp.clinicaMedica.repository.SecretariaRepository;
import com.iefp.clinicaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistoService {

    private final UtilizadorRepository utilizadorRepository;
    private final PacienteRepository pacienteRepository;
    private final SecretariaRepository secretariaRepository;
    private final MedicoRepository medicoRepository;

    public RegistoService(UtilizadorRepository utilizadorRepository,
                          PacienteRepository pacienteRepository,
                          SecretariaRepository secretariaRepository,
                          MedicoRepository medicoRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
    }

    //Registar Paciente

    public void registarPaciente(String nome,
                                 String email,
                                 String senha,
                                 String perfil,
                                 LocalDate dataNascimento,
                                 String telefone,
                                 String endereco) {


        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                perfil,
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Paciente paciente = new Paciente(null, utilizador);

        pacienteRepository.save(paciente);
    }

    //Registar Médico

    public void registarMedico(String nome,
                               String email,
                               String senha,
                               String perfil,
                               LocalDate dataNascimento,
                               String telefone,
                               String endereco,
                               String especialidade) {

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                perfil,
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Medico medico = new Medico(null, especialidade, utilizador);

        medicoRepository.save(medico);

    }

    //Registar Secretária

    public void registarSecretaria(String nome,
                                   String email,
                                   String senha,
                                   String perfil,
                                   LocalDate dataNascimento,
                                   String telefone,
                                   String endereco) {

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                perfil,
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Secretaria secretaria = new Secretaria(null, utilizador);

        secretariaRepository.save(secretaria);
    }
}
