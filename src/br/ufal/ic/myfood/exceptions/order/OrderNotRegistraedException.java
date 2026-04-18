package br.ufal.ic.myfood.exceptions.order;

public class OrderNotRegistraedException extends Exception {
    public OrderNotRegistraedException() {
        super("Nao existe pedido em aberto");
    }
}
