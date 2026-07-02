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
public class EditarService {
    private final UtilizadorRepository utilizadorRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final SecretariaRepository secretariaRepository;

    public EditarService(UtilizadorRepository utilizadorRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, SecretariaRepository secretariaRepository) {
        this.utilizadorRepository = utilizadorRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.secretariaRepository = secretariaRepository;

    }

    //Editar Paciente

    public void editarPaciente(Long pacienteId,
                               String name,
                               String email,
                               String senha,
                               LocalDate dataNascimento,
                               String telefone,
                               String endereco) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Utilizador utilizador = paciente.getUtilizador();

        utilizador.setNome(name);
        utilizador.setEmail(email);
        utilizador.setSenha(senha);
        utilizador.setDataNascimento(dataNascimento);
        utilizador.setTelefone(telefone);
        utilizador.setEndereco(endereco);

        utilizadorRepository.save(utilizador);
    }

    //Editar Médico

    public void editarMedico(Long medicoId,
                             String name,
                             String email,
                             String senha,
                             LocalDate dataNascimento,
                             String telefone,
                             String endereco,
                             String especialidade) {

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Utilizador utilizador = medico.getUtilizador();

        utilizador.setNome(name);
        utilizador.setEmail(email);
        utilizador.setSenha(senha);
        utilizador.setDataNascimento(dataNascimento);
        utilizador.setTelefone(telefone);
        utilizador.setEndereco(endereco);

        medico.setEspecialidade(especialidade);

        utilizadorRepository.save(utilizador);
        medicoRepository.save(medico);
    }

    //Editar Secretária

    public void editarSecretaria(Long secretariaId,
                                 String name,
                                 String email,
                                 String senha,
                                 LocalDate dataNascimento,
                                 String telefone,
                                 String endereco) {
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() -> new RuntimeException("Secretária não encontrada"));

        Utilizador utilizador = secretaria.getUtilizador();

        utilizador.setNome(name);
        utilizador.setEmail(email);
        utilizador.setSenha(senha);
        utilizador.setDataNascimento(dataNascimento);
        utilizador.setTelefone(telefone);
        utilizador.setEndereco(endereco);

        utilizadorRepository.save(utilizador);
        secretariaRepository.save(secretaria);
    }
}
