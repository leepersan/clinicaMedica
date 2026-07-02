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

@Service
public class RemoverService {

    private final UtilizadorRepository utilizadorRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final SecretariaRepository secretariaRepository;

    public RemoverService(UtilizadorRepository utilizadorRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, SecretariaRepository secretariaRepository) {
        this.utilizadorRepository = utilizadorRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.secretariaRepository = secretariaRepository;
    }

    //remover paciente
    public void removerPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Utilizador utilizador = paciente.getUtilizador();

        pacienteRepository.delete(paciente);
        utilizadorRepository.delete(utilizador);
    }

    //remover medico
    public void removerMedico(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Utilizador utilizador = medico.getUtilizador();

        medicoRepository.delete(medico);
        utilizadorRepository.delete(utilizador);
    }

    //remover secretária
    public void removerSecretaria(Long secretariaId) {
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() -> new RuntimeException("Secretária não encontrada"));

        Utilizador utilizador = secretaria.getUtilizador();

        secretariaRepository.delete(secretaria);
        utilizadorRepository.delete(utilizador);
    }

}
