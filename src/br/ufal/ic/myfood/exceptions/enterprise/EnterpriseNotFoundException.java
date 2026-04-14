package br.ufal.ic.myfood.exceptions.enterprise;

public class EnterpriseNotFoundException extends Exception{
    public EnterpriseNotFoundException() {
        super("Empresa nao encontrada");
    }
}
