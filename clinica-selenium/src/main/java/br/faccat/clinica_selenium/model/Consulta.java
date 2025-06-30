package br.faccat.clinica_selenium.model;

import java.time.LocalDateTime;

public class Consulta {
    private String paciente;
    private LocalDateTime dataHora;

    public Consulta(String paciente, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.dataHora = dataHora;
    }

    public String getPaciente() {
        return this.paciente;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }
}
