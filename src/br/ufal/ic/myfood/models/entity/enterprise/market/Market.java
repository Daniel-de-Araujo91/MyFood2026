package br.ufal.ic.myfood.models.entity.enterprise.market;

public class Market {
    private String enterpriseType;
    private String owner;
    private String name;
    private String address;
    private String openHour;
    private String closeHour;
    private String marketType;

    public Market(String enterpriseType, String owner, String name, String address, String openHour, String closeHour, String marketType) {
        this.enterpriseType = enterpriseType;
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.marketType = marketType;
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

    public String getOpenHour() {
        return openHour;
    }

    public String getClouseHour() {
        return closeHour;
    }

    public String getMarketType() {
        return marketType;
    }
}
