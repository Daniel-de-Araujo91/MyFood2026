package br.ufal.ic.myfood.exceptions.enterprise;

public class InvalidEnterpriseTypeException extends Exception {
    public InvalidEnterpriseTypeException() {
        super("Tipo de empresa invalido");
    }
}
