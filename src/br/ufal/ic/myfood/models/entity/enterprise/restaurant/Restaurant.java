package br.ufal.ic.myfood.models.entity.enterprise.restaurant;

import br.ufal.ic.myfood.models.entity.enterprise.Enterprise;

public class Restaurant extends Enterprise {
    private String kitchenType;

    public Restaurant(String enterpriseType, String owner, String name, String address, String kitchenType) {
        super(enterpriseType, owner, name, address);
        this.kitchenType = kitchenType;
    }

    public String getKitchenType() {
        return kitchenType;
    }
}
