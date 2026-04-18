package br.ufal.ic.myfood.exceptions.order;

public class ProductNotFoundInOrderException extends Exception{
    public ProductNotFoundInOrderException() {
        super("Produto nao encontrado");
    }
}
