package br.ufal.ic.myfood.exceptions.user;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(){
        super("Endereco invalido");
    }
}
