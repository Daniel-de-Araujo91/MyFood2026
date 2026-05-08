package br.ufal.ic.myfood.models.entity.user.deliverer;

import br.ufal.ic.myfood.models.entity.user.User;

public class Deliverer extends User {
    private String vehicle;
    private String plate;
    private String enterprises;
    private String order;

    public Deliverer(String name, String email, String password, String address, String vehicle, String plate) {
        super(name, email, password, address);
        this.vehicle = vehicle;
        this.plate = plate;
        this.enterprises ="[]";
        this.order ="[]";
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getPlate() {
        return plate;
    }

    public String getEnterprises() {
        return enterprises;
    }

    public String getOrder() {
        return order;
    }
}
