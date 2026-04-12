package br.ufal.ic.myfood.exceptions.user;

public class InvalidCpfFormatException extends Exception{

    public InvalidCpfFormatException(){
        super("CPF invalido");
    }
}
