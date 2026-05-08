package br.ufal.ic.myfood.exceptions.delivery;

public class DeliveryByOrderNotFoundException extends Exception {
    public DeliveryByOrderNotFoundException() {

        super("Nao existe entrega com esse id");
    }
}
