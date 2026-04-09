package br.ufal.ic.myfood.exceptions;

public class InvalidCpfFormatException extends Exception{

    public InvalidCpfFormatException(){
        super("CPF invalido");
    }
}
