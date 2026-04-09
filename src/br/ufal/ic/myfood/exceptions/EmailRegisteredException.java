package br.ufal.ic.myfood.exceptions;

public class EmailRegisteredException extends Exception{
    public EmailRegisteredException(){
        super("Conta com esse email ja existe");
    }
}
