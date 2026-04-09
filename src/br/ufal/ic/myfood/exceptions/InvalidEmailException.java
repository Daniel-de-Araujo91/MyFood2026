package br.ufal.ic.myfood.exceptions;

public class InvalidEmailException extends  Exception{
    public InvalidEmailException(){
        super("Email invalido");
    }
}
