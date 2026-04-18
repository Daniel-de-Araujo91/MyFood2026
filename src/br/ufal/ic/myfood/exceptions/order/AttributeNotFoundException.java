package br.ufal.ic.myfood.exceptions.order;

public class AttributeNotFoundException extends Exception{
    public AttributeNotFoundException() {
        super("Atributo nao existe");
    }
}
