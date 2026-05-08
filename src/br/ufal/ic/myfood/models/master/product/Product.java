package br.ufal.ic.myfood.models.master.product;

public class Product {
    private String enterpriseId;
    private String name;
    private String value;
    private String category;

    public Product(String enterpriseId, String name, String value, String category) {
        this.enterpriseId = enterpriseId;
        this.name = name;
        this.value = value;
        this.category = category;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }
}
