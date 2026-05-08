package br.ufal.ic.myfood.exceptions.delivery;

public class OrderIsNotDone extends Exception {
    public OrderIsNotDone() {
        super("Pedido nao esta pronto para entrega");
    }
}
