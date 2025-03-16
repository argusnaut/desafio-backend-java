package br.com.argusnaut.desafio_backend_java.exception;

public class FuncionarioNotFoundException extends RuntimeException {
    public FuncionarioNotFoundException(String funcionarioId) {
        super("Funcionário não encontrado com ID: " + funcionarioId);
    }
}
