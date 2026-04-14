package br.ufal.ic.myfood.exceptions.product;

public class InvalidValueException extends Exception{
    public InvalidValueException() {
        super("Valor invalido");
    }
}
