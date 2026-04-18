package br.ufal.ic.myfood.exceptions.order;

public class InvalidOrderAttributeException extends Exception{
    public InvalidOrderAttributeException() {
        super("Atributo invalido");
    }
}
