package br.ufal.ic.myfood.exceptions.order;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() {
        super("Pedido nao encontrado");
    }
}
