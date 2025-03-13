package br.com.argusnaut.desafio_backend_java.repository;

import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
}
