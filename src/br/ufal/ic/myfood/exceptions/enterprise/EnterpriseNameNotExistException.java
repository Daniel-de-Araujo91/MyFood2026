package br.ufal.ic.myfood.exceptions.enterprise;

public class EnterpriseNameNotExistException extends Exception{
    public EnterpriseNameNotExistException() {
        super("Nao existe empresa com esse nome");
    }
}
