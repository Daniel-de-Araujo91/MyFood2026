package br.ufal.ic.myfood.exceptions.user.deliverer;

public class InvalidDelivererException extends Exception {
    public InvalidDelivererException() {
        super("Nao e um entregador valido");
    }
}
