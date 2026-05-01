package br.ufal.ic.myfood.models.data.enterprise.restaurant;



import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.entity.enterprise.restaurant.Restaurant;

public class RestaurantDataBase extends EnterpriseDataBase {


    public static void addToBase(String eid, Restaurant restaurantUnregisted) throws Exception{
        String content  = EntepriseReadAll();

        String newRegister = "    { \"eid\": \"" + eid + "\", \"tipoEmpresa\": \"" + restaurantUnregisted.getEnterpriseType() + "\", \"dono\": \"" + restaurantUnregisted.getOwner() + "\", \"nome\": \"" + restaurantUnregisted.getName() + "\", \"endereco\": \"" + restaurantUnregisted.getAddress() + "\", \"tipoCozinha\": \"" + restaurantUnregisted.getKitchenType() +"\" }";

        addToBase(content,newRegister, EnterpriseDataBase.getArquive());
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = EntepriseReadAll();

        removeFromBase(id, content,EnterpriseDataBase.getArquive());
    }



}
