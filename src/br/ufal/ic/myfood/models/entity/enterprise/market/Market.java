package br.ufal.ic.myfood.models.entity.enterprise.market;

import br.ufal.ic.myfood.models.entity.enterprise.Enterprise;

public class Market extends Enterprise {
    private String openHour;
    private String closeHour;
    private String marketType;

    public Market(String enterpriseType, String owner, String name, String address, String openHour, String closeHour, String marketType) {
        super(enterpriseType, owner, name, address);
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.marketType = marketType;
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
