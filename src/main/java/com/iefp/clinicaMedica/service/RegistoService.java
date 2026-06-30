package com.iefp.clinicaMedica.service;

import com.iefp.clinicaMedica.model.*;
import com.iefp.clinicaMedica.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class RegistoService {

    private final UtilizadorRepository utilizadorRepository;
    private final PacienteRepository pacienteRepository;
    private final SecretariaRepository secretariaRepository;
    private final MedicoRepository medicoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final ConsultaRepository consultaRepository;
    private final ReceitaRepository receitaRepository;
    private final ExameRepository exameRepository;

    public RegistoService(UtilizadorRepository utilizadorRepository,
                          PacienteRepository pacienteRepository,
                          SecretariaRepository secretariaRepository,
                          MedicoRepository medicoRepository, DisponibilidadeRepository disponibilidadeRepository, ConsultaRepository consultaRepository, ReceitaRepository receitaRepository, ExameRepository exameRepository) {
        this.utilizadorRepository = utilizadorRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.consultaRepository = consultaRepository;
        this.receitaRepository = receitaRepository;
        this.exameRepository = exameRepository;
    }

    //Registar Paciente
    public void registarPaciente(String nome,
                                 String email,
                                 String senha,
                                 LocalDate dataNascimento,
                                 String telefone,
                                 String endereco) {
        if (utilizadorRepository.existsByEmail(email)) {
            throw new RuntimeException("Já existe um utilizador com esse email");
        }

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "PACIENTE",
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Paciente paciente = new Paciente(
                null,
                utilizador);

        pacienteRepository.save(paciente);
    }

    //Regista Medico
    public void registarMedico(String nome,
                               String email,
                               String senha,
                               LocalDate dataNascimento,
                               String telefone,
                               String endereco,
                               String especialidade) {
        if (utilizadorRepository.existsByEmail(email)) {
            throw new RuntimeException("Já existe um utilizador com esse email");
        }

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "MEDICO",
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Medico medico = new Medico(
                null,
                especialidade,
                utilizador);

        medicoRepository.save(medico);
    }

    //Registar Secretária
    public void registarSecretaria(String nome,
                                   String email,
                                   String senha,
                                   LocalDate dataNascimento,
                                   String telefone,
                                   String endereco
    ) {
        if (utilizadorRepository.existsByEmail(email)) {
            throw new RuntimeException("Já existe um utilizador com esse email");
        }

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "SECRETARIA",
                dataNascimento,
                telefone,
                endereco
        );

        utilizadorRepository.save(utilizador);

        Secretaria secretaria = new Secretaria(
                null,
                utilizador);
        secretariaRepository.save(secretaria);
    }

    //Registar disponibilidade
    public void criarDisponibilidade(Long medicoId,
                                     LocalDate data,
                                     LocalTime horaInicioTrabalho,
                                     LocalTime horaFimTrabalho) {
        Medico medico = medicoRepository.findById(medicoId).orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (!horaInicioTrabalho.isBefore(horaFimTrabalho)) {
            throw new RuntimeException("A hora de início deve ser anterior à hota de fim.");
        }

        LocalTime horaAtual = horaInicioTrabalho;

        while (horaAtual.plusHours(1).isBefore(horaFimTrabalho) ||
                horaAtual.plusHours(1).equals(horaFimTrabalho)) {

            LocalTime horaFimConsulta = horaAtual.plusHours(1);

            Boolean jaExiste = disponibilidadeRepository
                    .existsByMedico_IdAndDataAndHoraInicioAndHoraFim(medicoId, data, horaAtual, horaFimTrabalho);

            if (!jaExiste) {
                Disponibilidade disponibilidade = new Disponibilidade(
                        null,
                        medico,
                        data,
                        horaAtual,
                        horaFimConsulta,
                        false
                );

                disponibilidadeRepository.save(disponibilidade);
            }
            horaAtual = horaAtual.plusHours(1);

        }
    }

    //Registar consulta
    public void marcarConsulta(Long disponibilidadeId,
                               Long pacienteId,
                               Long secretariaId) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(disponibilidadeId)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada."));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() -> new RuntimeException("Secretária não encontrada"));

        Consulta consulta = new Consulta(
                null,
                disponibilidade,
                paciente,
                secretaria,
                disponibilidade.getData(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraFim(),
                "MARCADA"
        );

        consultaRepository.save(consulta);

        disponibilidade.setOcupada(true);
        disponibilidadeRepository.save(disponibilidade);

    }

    //Registar receita

    public void criarReceita(Long consultaId,
                             String medicamento,
                             String dosagem,
                             String instrucoes){

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(()->new RuntimeException("Consulta não encontrada"));

        Receita receita = new Receita(
                null,
                medicamento,
                dosagem,
                consulta,
                instrucoes
        );
        receitaRepository.save(receita);
    }

    //Criar exame

    public void criarExame(Long consultaId,
                           String tipoExame,
                           String descricao,
                           String resultado){

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(()->new RuntimeException("Consulta não encontrada"));

        Exame exame = new Exame(
                null,
                tipoExame,
                descricao,
                resultado,
                consulta
        );
        exameRepository.save(exame);
    }

}
