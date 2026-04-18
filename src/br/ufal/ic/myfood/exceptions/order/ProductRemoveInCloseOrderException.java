package br.ufal.ic.myfood.exceptions.order;

public class ProductRemoveInCloseOrderException extends Exception{
    public ProductRemoveInCloseOrderException() {
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
