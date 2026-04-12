package br.ufal.ic.myfood.exceptions.user;

public class InvalidEmailException extends  Exception{
    public InvalidEmailException(){
        super("Email invalido");
    }
}
