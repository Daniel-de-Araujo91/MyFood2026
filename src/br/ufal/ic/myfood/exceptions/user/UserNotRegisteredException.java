package br.ufal.ic.myfood.exceptions.user;

public class UserNotRegisteredException extends Exception{

    public UserNotRegisteredException(){
        super("Usuario nao cadastrado.");
    }
}
