package br.ufal.ic.myfood.exceptions.user;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Senha invalido");
    }
}
