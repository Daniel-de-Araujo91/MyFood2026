package br.ufal.ic.myfood.exceptions.order;

public class ProductNotRegisteredInEnterpriseException extends Exception{
    public ProductNotRegisteredInEnterpriseException() {
        super("O produto nao pertence a essa empresa");
    }
}
