package br.ufal.ic.myfood.models.data.enterprise.restaurant;


import br.ufal.ic.myfood.exceptions.enterprise.NameEnterpriseRegisteredException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.master.enterprise.restaurant.Restaurant;

public class RestaurantDataBase extends EnterpriseDataBase {


    public static void addToBase(String eid, Restaurant restaurantUnregisted) throws Exception{
        String content  = readAll();

        String newRegister = "    { \"eid\": \"" + eid + "\", \"tipoEmpresa\": \"" + restaurantUnregisted.getEnterpriseType() + "\", \"dono\": \"" + restaurantUnregisted.getOwner() + "\", \"nome\": \"" + restaurantUnregisted.getName() + "\", \"endereco\": \"" + restaurantUnregisted.getAddress() + "\", \"tipoCozinha\": \"" + restaurantUnregisted.getKitchenType() +"\" }";

        int close  = content.lastIndexOf("]");
        int open   = content.indexOf("[");
        String inside = content.substring(open + 1, close).trim();

        String newContent;
        if(inside.isEmpty()){
            newContent =    content.substring(0, close) + "\n" + newRegister + "\n  "
                    + content.substring(close);
        } else{
            newContent =    content.substring(0, close) + ",\n" + newRegister + "\n  "
                    + content.substring(close);
        }

        createEnterpriseBase(newContent);
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = readAll();

        String target = "\"" + id + "\"";
        int indexId = content.indexOf(target);
        if(indexId != -1){
            throw  new NameEnterpriseRegisteredException();
        }
        int begin = content.indexOf("{",  indexId);
        int end = content.indexOf("}",  indexId);

        String before =  content.substring(0, begin).stripTrailing();
        String after =  content.substring(end);

        if(before.endsWith(",")){
            before = before.substring(0, before.length()-1);
        }else if(after.stripLeading().startsWith(",")){
            after = after.stripLeading().substring(1);
        }

        createEnterpriseBase(before + after);
    }



}
