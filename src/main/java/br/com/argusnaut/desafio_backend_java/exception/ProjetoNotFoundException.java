package br.com.argusnaut.desafio_backend_java.exception;

public class ProjetoNotFoundException extends RuntimeException {

    public ProjetoNotFoundException(String projetoId) {
        super("Projeto não encontrado com ID: " + projetoId);
    }
}
