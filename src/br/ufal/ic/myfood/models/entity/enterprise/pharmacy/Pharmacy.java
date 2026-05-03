package br.ufal.ic.myfood.models.entity.enterprise.pharmacy;

import br.ufal.ic.myfood.models.entity.enterprise.Enterprise;

public class Pharmacy extends Enterprise {
    private String open24h;
    private String numberOfEmployees;

    public Pharmacy(String enterpriseType, String owner, String name, String address, String open24h, String numberOfEmployees) {
        super(enterpriseType, owner, name, address);
        this.open24h = open24h;
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getOpen24h() {
        return open24h;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }
}
