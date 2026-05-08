package br.ufal.ic.myfood.exceptions.order;

public class OrderStatusIsOpenExxception extends Exception {
    public OrderStatusIsOpenExxception() {
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
