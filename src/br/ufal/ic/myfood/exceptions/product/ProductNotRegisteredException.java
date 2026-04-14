package br.ufal.ic.myfood.exceptions.product;

public class ProductNotRegisteredException extends Exception {
    public ProductNotRegisteredException() {
        super("Produto nao cadastrado");
    }
}
