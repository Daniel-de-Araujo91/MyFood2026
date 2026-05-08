package br.ufal.ic.myfood.exceptions.delivery;

public class DelivererInRouteException extends Exception {
    public DelivererInRouteException() {
        super("Entregador ainda em entrega");
    }
}
