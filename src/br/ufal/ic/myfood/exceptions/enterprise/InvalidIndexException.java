package br.ufal.ic.myfood.exceptions.enterprise;

public class InvalidIndexException extends Exception{
    public InvalidIndexException() {
        super("Indice invalido");
    }
}
