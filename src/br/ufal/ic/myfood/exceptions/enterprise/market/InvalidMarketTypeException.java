package br.ufal.ic.myfood.exceptions.enterprise.market;

public class InvalidMarketTypeException extends Exception {
    public InvalidMarketTypeException() {
        super("Tipo de mercado invalido");
    }
}
