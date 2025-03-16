package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.dto.FuncionarioDTO;
import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @PostMapping
    public FuncionarioDTO createFuncionario(@RequestBody Funcionario funcionario) {
        return service.createFuncionario(funcionario);
    }

    @GetMapping
    public List<FuncionarioDTO> getAllFuncionarios() {
        return service.getAllFuncionarios();
    }

    @GetMapping("/{id}")
    public FuncionarioDTO getFuncionarioById(@PathVariable UUID id) {
        return service.getFuncionarioById(id);
    }
}
