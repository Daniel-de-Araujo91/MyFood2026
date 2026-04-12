package br.ufal.ic.myfood.exceptions.enterprise;

public class NameAndAddressRegisteredException extends Exception{

    public NameAndAddressRegisteredException(){
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
