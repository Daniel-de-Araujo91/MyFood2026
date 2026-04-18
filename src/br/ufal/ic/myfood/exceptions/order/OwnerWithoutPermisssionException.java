package br.ufal.ic.myfood.exceptions.order;

public class OwnerWithoutPermisssionException extends Exception{
    public OwnerWithoutPermisssionException() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
