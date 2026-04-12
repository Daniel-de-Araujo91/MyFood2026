package br.ufal.ic.myfood.models.master.enterprise.restaurant;

public class Restaurant {
    private String enterpriseType;
    private String owner;
    private String name;
    private String address;
    private String kitchenType;

    public Restaurant(String enterpriseType, String owner, String name, String address, String kitchenType) {
        this.enterpriseType = enterpriseType;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.kitchenType = kitchenType;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getKitchenType() {
        return kitchenType;
    }
}
