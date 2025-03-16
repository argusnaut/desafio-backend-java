package br.com.argusnaut.desafio_backend_java.model;

import br.com.argusnaut.desafio_backend_java.exception.InvalidCpfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    public void testSetCpf_FormatoCorreto() {
        Funcionario funcionario = new Funcionario();
        String cpf = "123.456.789-09";

        funcionario.setCpf(cpf);

        assertEquals("123.456.789-09", funcionario.getCpf());
    }

    @Test
    public void testSetCpf_FormatoSemPontosETracos() {
        Funcionario funcionario = new Funcionario();
        String cpf = "12345678909";

        funcionario.setCpf(cpf);

        assertEquals("123.456.789-09", funcionario.getCpf());
    }

    @Test
    public void testSetCpf_CpfInvalido() {
        Funcionario funcionario = new Funcionario();
        String cpf = "1234567890"; // CPF com menos de 11 dígitos

        InvalidCpfException exception = assertThrows(InvalidCpfException.class, () -> {
            funcionario.setCpf(cpf);
        });

        assertEquals("O CPF deve conter exatamente 11 números", exception.getMessage());
    }

    @Test
    public void testSetCpf_CpfInvalidoLetras() {
        Funcionario funcionario = new Funcionario();
        String cpf = "1234567890A"; // CPF letras

        InvalidCpfException exception = assertThrows(InvalidCpfException.class, () -> {
            funcionario.setCpf(cpf);
        });

        assertEquals("O CPF deve conter exatamente 11 números", exception.getMessage());
    }

    @Test
    public void testSetCpf_CpfNulo() {
        Funcionario funcionario = new Funcionario();
        String cpf = null;

        assertThrows(NullPointerException.class, () -> {
            funcionario.setCpf(cpf);
        });
    }
}