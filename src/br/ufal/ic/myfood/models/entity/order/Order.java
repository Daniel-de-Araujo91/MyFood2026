package br.ufal.ic.myfood.models.entity.order;

public class Order {
    private String user;
    private String enterprise;
    private String status;
    private String value;
    private String products;

    public Order(String user, String enterprise) {
        this.user = user;
        this.enterprise = enterprise;
        this.status = "aberto";
        this.value = "0.00";
        this.products = "[]";
    }

    public String getUser() {
        return user;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public String getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    public String getProducts() {
        return products;
    }
}
