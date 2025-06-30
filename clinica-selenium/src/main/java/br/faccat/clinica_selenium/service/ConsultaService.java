package br.faccat.clinica_selenium.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.faccat.clinica_selenium.model.Paciente;
import org.springframework.stereotype.Service;

import br.faccat.clinica_selenium.model.Consulta;



@Service 
public class ConsultaService {
    private final List<Consulta> consultas = new ArrayList<>();

    private final Map<String, Paciente> pacientes = new HashMap<>();


    public Consulta agendar(String nome, String cpf, String dataHoraTexto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto, formatter);

            Paciente paciente = pacientes.computeIfAbsent(cpf, k -> new Paciente(nome, cpf));

            Consulta consulta = new Consulta(paciente.toString(), dataHora);
            consultas.add(consulta);
            return consulta;

        } catch (Exception e) {
            throw new RuntimeException("Formato de data inválido: " + dataHoraTexto);
        }
    }

    public List<Consulta> listar() {
        return new ArrayList<>(consultas);
    }
}