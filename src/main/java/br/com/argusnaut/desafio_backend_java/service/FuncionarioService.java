package br.com.argusnaut.desafio_backend_java.service;

import br.com.argusnaut.desafio_backend_java.dto.FuncionarioDTO;
import br.com.argusnaut.desafio_backend_java.exception.FuncionarioNotFoundException;
import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import br.com.argusnaut.desafio_backend_java.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public FuncionarioDTO createFuncionario(@Valid Funcionario funcionario) {
        return FuncionarioDTO.toDTO(repository.save(funcionario));
    }

    public List<FuncionarioDTO> getAllFuncionarios() {
        return repository.findAll().stream()
                .map(FuncionarioDTO::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioDTO getFuncionarioById(UUID id) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);
        return funcionarioOptional.map(FuncionarioDTO::toDTO).orElseThrow(() -> new FuncionarioNotFoundException(id.toString()));
    }

}
