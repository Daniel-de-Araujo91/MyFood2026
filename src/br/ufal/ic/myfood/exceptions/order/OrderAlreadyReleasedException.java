package br.ufal.ic.myfood.exceptions.order;

public class OrderAlreadyReleasedException extends Exception {
    public OrderAlreadyReleasedException() {
        super("Pedido ja liberado");
    }
}
