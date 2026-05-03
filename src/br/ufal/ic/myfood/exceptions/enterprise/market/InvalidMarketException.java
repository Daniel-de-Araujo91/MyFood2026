package br.ufal.ic.myfood.exceptions.enterprise.market;

public class InvalidMarketException extends Exception {
    public InvalidMarketException() {
        super("Nao e um mercado valido");
    }
}
