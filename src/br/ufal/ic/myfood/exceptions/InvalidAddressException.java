package br.ufal.ic.myfood.exceptions;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(){
        super("Endereco invalido");
    }
}
