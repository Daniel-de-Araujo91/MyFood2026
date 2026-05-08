package br.ufal.ic.myfood.models.entity.delivery;

import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.order.OrderDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;

public class Delivery {
    private String orderId;
    private String delivererName;
    private String destination;
    private String enterpriseName;
    private String enterpriseId;
    private String userId;
    private String userName;
    private String products;

    public Delivery(String orderId, String delivererId, String destination) throws Exception {
        this.orderId = orderId;
        this.delivererName = UserDataBase.searchBase("id", delivererId, "nome", 0, "attribute");
        this.userId = OrderDataBase.searchBase("numero", orderId, "cliente",0, "attribute");
        if(destination == null || destination.equals("")) {
            destination = UserDataBase.searchBase("id", userId, "endereco", 0, "attribute");
        }
        this.destination = destination;
        this.enterpriseId = OrderDataBase.searchBase("numero", orderId, "empresa",0, "attribute");
        this.enterpriseName = EnterpriseDataBase.searchBase("eid", enterpriseId, "nome",0, "attribute");
        this.userName = UserDataBase.searchBase("id", userId, "nome",0, "attribute");
        this.products = OrderDataBase.searchBase("numero", orderId, "produtos",0, "attribute");
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDelivererName() {
        return delivererName;
    }

    public String getDestination() {
        return destination;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProducts() {
        return products;
    }
}
