package com.example.testeUnitario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.testeUnitario.entity.Pessoa;
import com.example.testeUnitario.service.PessoaService;

@RestController
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

   
    @GetMapping("/cpf/{cpf}")
    @CrossOrigin(allowedHeaders = "*")
    public ResponseEntity<List<Pessoa>> buscaDadosProfissionais(@PathVariable("cpf") String cpf) {
  

        return ResponseEntity.ok(service.findByCpf(cpf));

    }
}