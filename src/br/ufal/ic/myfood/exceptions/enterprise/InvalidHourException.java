package br.ufal.ic.myfood.exceptions.enterprise;

public class InvalidHourException extends Exception {
    public InvalidHourException() {
        super("Horario invalido");
    }
}
