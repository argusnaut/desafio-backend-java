package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository repository;

    public Projeto createProjeto(Projeto projeto) {
        return repository.save(projeto);
    }

    public List<Projeto> getAllProjetos() {
        return repository.findAll();
    }

    public Projeto getProjetoById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}