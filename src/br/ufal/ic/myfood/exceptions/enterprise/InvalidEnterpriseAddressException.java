package br.ufal.ic.myfood.exceptions.enterprise;

public class InvalidEnterpriseAddressException extends Exception{
    public InvalidEnterpriseAddressException(){
        super("Endereco da empresa invalido");
    }
}
