package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return service.createFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return service.getAllFuncionarios();
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable UUID id) {
        return service.getFuncionarioById(id);
    }
}
