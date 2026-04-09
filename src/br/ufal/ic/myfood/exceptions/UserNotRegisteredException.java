package br.ufal.ic.myfood.exceptions;

public class UserNotRegisteredException extends Exception{

    public UserNotRegisteredException(){
        super("Usuario nao cadastrado.");
    }
}
