package br.ufal.ic.myfood.exceptions.product;

public class InvalidProductAttributeException extends Exception{
    public InvalidProductAttributeException() {
        super("Atributo nao existe");
    }
}
