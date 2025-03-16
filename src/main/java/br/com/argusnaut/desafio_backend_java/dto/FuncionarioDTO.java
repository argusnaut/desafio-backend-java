package br.com.argusnaut.desafio_backend_java.dto;


import br.com.argusnaut.desafio_backend_java.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuncionarioDTO {
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private BigDecimal salario;

    public static Funcionario toDomain(FuncionarioDTO dto) {
        return Funcionario.builder().id(dto.getId()).nome(dto.getNome()).cpf(dto.getCpf()).email(dto.getEmail())
                .salario(dto.getSalario()).build();
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario) {
        return FuncionarioDTO.builder().id(funcionario.getId()).nome(funcionario.getNome()).cpf(funcionario.getCpf())
                .email(funcionario.getEmail()).salario(funcionario.getSalario()).build();
    }
}
