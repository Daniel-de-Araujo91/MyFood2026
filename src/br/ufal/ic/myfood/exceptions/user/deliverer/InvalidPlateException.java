package br.ufal.ic.myfood.exceptions.user.deliverer;

public class InvalidPlateException extends Exception{
    public InvalidPlateException() {
        super("Placa invalido");
    }
}
