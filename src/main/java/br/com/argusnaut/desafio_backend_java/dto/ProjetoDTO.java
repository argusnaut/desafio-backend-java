package br.com.argusnaut.desafio_backend_java.dto;

import br.com.argusnaut.desafio_backend_java.model.Projeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoDTO {
    private UUID id;
    private String nome;
    private LocalDateTime data_criacao;
    private List<FuncionarioDTO> funcionarios;

    public static ProjetoDTO toDTO(Projeto projeto) {
        return ProjetoDTO.builder().id(projeto.getId()).nome(projeto.getNome()).data_criacao(projeto.getData_criacao())
                .funcionarios(projeto.getFuncionarios().stream().map(FuncionarioDTO::toDTO).toList()).build();
    }
}
