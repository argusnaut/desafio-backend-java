package br.com.argusnaut.desafio_backend_java.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Representa um projeto no sistema de gerenciamento de projetos.
 * Um projeto pode ter vários funcionários associados, e um funcionário pode estar em vários projetos.
 *
 * <p>A classe é mapeada para uma tabela no banco de dados usando anotações JPA.</p>
 *
 * <p><b>Atributos obrigatórios:</b></p>
 * <ul>
 *   <li><b>nome</b>: Nome do projeto (não pode ser nulo).</li>
 *   <li><b>data_criacao</b>: Data de criação do projeto (gerada automaticamente).</li>
 * </ul>
 *
 * <p><b>Relacionamento:</b></p>
 * <ul>
 *   <li>Many-to-Many com a classe {@link Funcionario}.</li>
 * </ul>
 *
 * @see Funcionario
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String nome;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime data_criacao;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "projeto_funcionario",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> funcionarios = new ArrayList<>();
}
