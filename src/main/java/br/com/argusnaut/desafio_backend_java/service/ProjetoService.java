package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.model.Projeto;
import br.com.argusnaut.desafio_backend_java.repository.FuncionarioRepository;
import br.com.argusnaut.desafio_backend_java.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Projeto createProjeto(Projeto projeto) {
        if (projeto.getFuncionarios() == null) {
            projeto.setFuncionarios(new ArrayList<>());
        }

        List<Funcionario> funcionariosToAssociate = new ArrayList<>();
        for (Funcionario funcionario : projeto.getFuncionarios()) {
            if (funcionario.getId() != null) {
                Funcionario existingFuncionario = funcionarioRepository.findById(funcionario.getId())
                        .orElseThrow(() -> new RuntimeException("Funcionario not found with id: " + funcionario.getId()));
                funcionariosToAssociate.add(existingFuncionario);
            } else {
                Funcionario newFuncionario = funcionarioRepository.save(funcionario);
                funcionariosToAssociate.add(newFuncionario);
            }
        }

        projeto.setFuncionarios(funcionariosToAssociate);

        return projetoRepository.save(projeto);
    }

    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    public Projeto getProjetoById(UUID id) {
        return projetoRepository.findById(id).orElse(null);
    }
}