package br.ufal.ic.myfood.exceptions.enterprise;

public class IndexMoreExpectedException extends Exception{
    public IndexMoreExpectedException() {
        super("Indice maior que o esperado");
    }
}
