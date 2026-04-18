package br.ufal.ic.myfood.exceptions.order;

public class InvalidProductException extends Exception{
    public InvalidProductException() {
        super("Produto invalido");
    }
}
