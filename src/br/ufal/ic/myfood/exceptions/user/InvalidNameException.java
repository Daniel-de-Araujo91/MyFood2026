package br.ufal.ic.myfood.exceptions.user;

public class InvalidNameException extends Exception{

    public InvalidNameException(){
        super("Nome invalido");
    }
}
