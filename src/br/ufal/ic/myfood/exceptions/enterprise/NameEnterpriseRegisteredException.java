package br.ufal.ic.myfood.exceptions.enterprise;

public class NameEnterpriseRegisteredException extends Exception{

    public NameEnterpriseRegisteredException(){
        super("Empresa com esse nome ja existe");
    }
}
