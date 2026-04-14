package br.ufal.ic.myfood.exceptions.product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Produto nao encontrado");
    }
}
