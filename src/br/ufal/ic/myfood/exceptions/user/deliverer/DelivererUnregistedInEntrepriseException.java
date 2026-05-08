package br.ufal.ic.myfood.exceptions.user.deliverer;

public class DelivererUnregistedInEntrepriseException extends RuntimeException {
    public DelivererUnregistedInEntrepriseException() {
      super("Entregador nao estar em nenhuma empresa.");
    }
}
