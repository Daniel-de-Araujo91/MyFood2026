package br.ufal.ic.myfood.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Senha invalido");
    }
}
