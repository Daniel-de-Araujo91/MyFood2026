package br.ufal.ic.myfood.exceptions.order;

public class OrderClosedException extends Exception{
    public OrderClosedException() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
