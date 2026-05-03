package br.ufal.ic.myfood.models.entity.enterprise;

public class Enterprise {
    protected String enterpriseType;
    protected String owner;
    protected String name;
    protected String address;

    public Enterprise(String enterpriseType, String owner, String name, String address) {
        this.enterpriseType = enterpriseType;
        this.owner = owner;
        this.name = name;
        this.address = address;
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
}
