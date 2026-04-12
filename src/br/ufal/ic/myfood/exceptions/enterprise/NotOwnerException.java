package br.ufal.ic.myfood.exceptions.enterprise;

public class NotOwnerException extends Exception{
    public NotOwnerException() {
        super("Usuario nao pode criar uma empresa");
    }
}
