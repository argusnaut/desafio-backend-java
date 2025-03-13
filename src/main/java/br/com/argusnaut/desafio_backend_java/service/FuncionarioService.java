package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public Funcionario createFuncionario(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return repository.findAll();
    }

    public Funcionario getFuncionarioById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
