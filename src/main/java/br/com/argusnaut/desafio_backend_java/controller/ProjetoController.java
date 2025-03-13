package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService service;

    @PostMapping
    public Projeto createProjeto(@RequestBody Projeto projeto) {
        return service.createProjeto(projeto);
    }

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return service.getAllProjetos();
    }

    @GetMapping("/{id}")
    public Projeto getProjetoById(@PathVariable UUID id) {
        return service.getProjetoById(id);
    }
}
