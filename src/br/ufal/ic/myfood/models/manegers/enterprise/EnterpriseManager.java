package br.ufal.ic.myfood.models.manegers.enterprise;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.EnterpriseNotRegisteredException;
import br.ufal.ic.myfood.exceptions.enterprise.InvalidEnterpriseAttributeException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.enterprise.restaurant.RestaurantDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.manegers.enterprise.restaurant.RestaurantManeger;
import br.ufal.ic.myfood.models.master.enterprise.restaurant.Restaurant;


import java.util.UUID;
public class EnterpriseManager {


    public static String createEnterprise(String enterpriseType, String owner, String name, String address, String particulars) throws Exception {
        String eId = UUID.randomUUID().toString();

        if(enterpriseType.equals("restaurante")){

            RestaurantManeger.checkData(owner, name, address, particulars);
            Restaurant newRestaurant = new Restaurant(enterpriseType,owner,name, address, particulars);
            RestaurantDataBase.addToBase(eId, newRestaurant);
        }




        return eId;
    }

    public static String getEnterpriseAttribute(String eid, String attribute) throws Exception{
        try{
            EnterpriseDataBase.searchBase("eid", eid, "eid", 0, "attribute");
        }catch (DataNotFoundException e){
            throw new EnterpriseNotRegisteredException();
        }
        String enterpriseType = EnterpriseDataBase.searchBase("eid", eid, "tipoEmpresa", 0, "attribute");

        if(enterpriseType.equals("restaurante")){
            String listAttribute = "{[tipoEmpresa], [dono], [nome], [endereco], [tipoCozinha]}";

            if( attribute == null || attribute.trim().isEmpty()  ){
                throw new InvalidEnterpriseAttributeException();
            }else if(!listAttribute.contains(attribute)){
                throw new InvalidEnterpriseAttributeException();
            }
        }

        if(attribute.equals("dono")){
            String idOwner = EnterpriseDataBase.searchBase("eid", eid, attribute,0,"attribute");
            return UserDataBase.searchBase("id", idOwner, "nome", 0,"attribute");
        }

        String result = EnterpriseDataBase.searchBase("eid", eid, attribute,0,"attribute");

        return result;
    }
}
