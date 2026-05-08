package br.ufal.ic.myfood.exceptions.delivery;

public class DeliveryNotFoundException extends Exception {
    public DeliveryNotFoundException() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
