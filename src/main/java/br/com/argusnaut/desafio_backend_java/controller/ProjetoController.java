package br.com.argusnaut.desafio_backend_java.controller;

import br.com.argusnaut.desafio_backend_java.dto.ProjetoDTO;
import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService service;

    @PostMapping
    public ProjetoDTO createProjeto(@RequestBody Projeto projeto) {
        return service.createProjeto(projeto);
    }

    @GetMapping
    public List<ProjetoDTO> getAllProjetos() {
        return service.getAllProjetos();
    }

    @GetMapping("/{id}")
    public ProjetoDTO getProjetoById(@PathVariable UUID id) {
        return service.getProjetoById(id);
    }
}
