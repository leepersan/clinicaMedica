package com.iefp.clinicaMedica.service;

import com.iefp.clinicaMedica.model.*;
import com.iefp.clinicaMedica.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListagemService {

    private final UtilizadorRepository utilizadorRepository;
    private final SecretariaRepository secretariaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final ConsultaRepository consultaRepository;
    private final ExameRepository exameRepository;
    private final ReceitaRepository receitaRepository;

    public ListagemService(UtilizadorRepository utilizadorRepository,
                           SecretariaRepository secretariaRepository,
                           MedicoRepository medicoRepository,
                           PacienteRepository pacienteRepository,
                           DisponibilidadeRepository disponibilidadeRepository, ConsultaRepository consultaRepository, ExameRepository exameRepository, ReceitaRepository receitaRepository) {
        this.utilizadorRepository = utilizadorRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.consultaRepository = consultaRepository;
        this.exameRepository = exameRepository;
        this.receitaRepository = receitaRepository;
    }

    public List<Utilizador> listarUtilizadores() {
        return utilizadorRepository.findAll();
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public List<Secretaria> listarSecretarias() {
        return secretariaRepository.findAll();
    }

    public List<Disponibilidade> listarTodasDisponibilidades() {
        return disponibilidadeRepository.findAll();
    }

    public List<Disponibilidade> listarPorEspecialidade(String especialidade) {
        return disponibilidadeRepository.findByMedico_EspecialidadeIgnoreCaseAndOcupadaFalseOrderByDataAscHoraInicioAsc(especialidade);
    }

    public List<Consulta> listarTodasConsultas() {
        return consultaRepository.findAll();
    }

    public List<String> listarEspecialidades() {
        return medicoRepository.listarEspecialidades();
    }

    public List<Disponibilidade> listarDisponibilidadePorEspecialidade(String especialidade) {
        return disponibilidadeRepository
                .findByMedico_EspecialidadeIgnoreCaseAndOcupadaFalseOrderByDataAscHoraInicioAsc(especialidade);
    }

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public List<Exame> listarExames() {
        return exameRepository.findAll();
    }

}