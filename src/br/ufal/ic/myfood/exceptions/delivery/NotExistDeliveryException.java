package br.ufal.ic.myfood.exceptions.delivery;

public class NotExistDeliveryException extends Exception{
    public NotExistDeliveryException() {
        super("Nao existe pedido para entrega");
    }
}
