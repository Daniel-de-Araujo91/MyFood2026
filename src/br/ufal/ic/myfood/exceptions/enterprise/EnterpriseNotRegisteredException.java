package br.ufal.ic.myfood.exceptions.enterprise;

public class EnterpriseNotRegisteredException extends Exception {
    public EnterpriseNotRegisteredException() {
        super("Empresa nao cadastrada");
    }
}
