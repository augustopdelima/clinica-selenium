package br.faccat.clinica_selenium.controller;

import br.faccat.clinica_selenium.model.Consulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.faccat.clinica_selenium.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
   private final ConsultaService service;

   public ConsultaController(ConsultaService service) {
        this.service = service;
   }

   @PostMapping
   public ResponseEntity<String> agendar(
           @RequestParam(name = "paciente") String paciente,
           @RequestParam(name = "dataHora") String dataHora,
           @RequestParam(name = "cpf") String cpf
   ) {
       Consulta consulta = service.agendar(paciente,cpf, dataHora);
       return ResponseEntity.ok("Consulta agendada para " + consulta.getPaciente() + " em " + dataHora);
   }
}
