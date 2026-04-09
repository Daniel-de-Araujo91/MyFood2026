package br.ufal.ic.myfood.exceptions;

public class InvalidNameException extends Exception{

    public InvalidNameException(){
        super("Nome invalido");
    }
}
