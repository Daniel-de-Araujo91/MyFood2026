package br.ufal.ic.myfood.exceptions.user.deliverer;

public class UserIsNotDeliverer extends Exception{
    public UserIsNotDeliverer() {
        super("Usuario nao e um entregador");
    }
}
