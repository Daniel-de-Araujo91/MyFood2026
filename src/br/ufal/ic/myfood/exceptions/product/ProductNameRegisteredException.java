package br.ufal.ic.myfood.exceptions.product;

public class ProductNameRegisteredException extends Exception {
    public ProductNameRegisteredException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
