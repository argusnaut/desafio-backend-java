package br.com.argusnaut.desafio_backend_java.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException() {
        super("O CPF deve conter exatamente 11 números");
    }
}
