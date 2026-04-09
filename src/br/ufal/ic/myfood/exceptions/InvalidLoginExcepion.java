package br.ufal.ic.myfood.exceptions;

public class InvalidLoginExcepion extends Exception {

    public InvalidLoginExcepion(){
        super("Login ou senha invalidos");
    }
}
