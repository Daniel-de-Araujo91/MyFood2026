package br.ufal.ic.myfood.exceptions.order;

public class OrderUserByEnterpriseRegistraedException extends Exception{
    public OrderUserByEnterpriseRegistraedException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
