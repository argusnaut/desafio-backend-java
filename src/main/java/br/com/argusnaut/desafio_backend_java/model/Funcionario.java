package br.com.argusnaut.desafio_backend_java.model;

import br.com.argusnaut.desafio_backend_java.exception.InvalidCpfException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String nome;

    @NotNull
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF deve seguir o padrão '000.000.000-00' ou '00000000000'")
    private String cpf;

    @Email
    private String email;
    private BigDecimal salario;

    @ManyToMany(mappedBy = "funcionarios")
    @JsonIgnore
    private List<Projeto> projetos = new ArrayList<>();

    public void setCpf(String cpf) {
        if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            this.cpf = cpf;
        } else if (cpf.matches("\\d{11}")) {
            this.cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        } else {
            throw new InvalidCpfException();
        }
    }
}
