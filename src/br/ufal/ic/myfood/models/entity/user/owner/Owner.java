package br.ufal.ic.myfood.models.entity.user.owner;

import br.ufal.ic.myfood.models.entity.user.User;

public class Owner extends User {
    private String cpf;

    public Owner(String name, String email, String password, String address, String cpf) {
        super(name, email, password, address);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
