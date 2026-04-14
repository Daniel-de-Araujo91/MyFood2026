package br.ufal.ic.myfood.exceptions.product;

public class InvalidCategoryException extends Exception{
    public InvalidCategoryException() {
        super("Categoria invalido");
    }
}
