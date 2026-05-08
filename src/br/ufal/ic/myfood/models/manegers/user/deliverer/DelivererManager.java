package br.ufal.ic.myfood.models.manegers.user.deliverer;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.order.InvalidProductException;
import br.ufal.ic.myfood.exceptions.order.ProductNotFoundInOrderException;
import br.ufal.ic.myfood.exceptions.order.ProductRemoveInCloseOrderException;
import br.ufal.ic.myfood.exceptions.user.deliverer.InvalidPlateException;
import br.ufal.ic.myfood.exceptions.user.deliverer.InvalidVehicleException;
import br.ufal.ic.myfood.exceptions.user.deliverer.UserIsNotDeliverer;
import br.ufal.ic.myfood.models.data.delivery.DeliveryDataBase;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.order.OrderDataBase;
import br.ufal.ic.myfood.models.data.product.ProductDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.data.user.deliverer.DelivererDataBase;

public class DelivererManager {
    public static void  checkData(String vehicle, String plate) throws Exception {
        if(vehicle == null || vehicle.isBlank()){
            throw  new InvalidVehicleException();
        }
        try {
            if(plate == null || plate.isBlank() ||!UserDataBase.searchBase("placa", plate, "placa",0, "attribute").isBlank()){
                throw new InvalidPlateException();
            }
        }catch (DataNotFoundException e){}
    }

    public static void registerDeliverer(String enterpriseId, String delivererId) throws Exception {
        try{
            if (!UserDataBase.searchBase("id", delivererId, "veiculo", 0, "attribute").isBlank() || !UserDataBase.searchBase("id", delivererId, "placa", 0, "attribute").isBlank());


            EnterpriseDataBase.addDelivererToEnterprise(enterpriseId,delivererId);
        }catch (DataNotFoundException e){
            throw new UserIsNotDeliverer();
        }
    }

    public static String getDelivererByEnterprise(String enterpriseId) throws Exception {
        return "{"+EnterpriseDataBase.searchBase("eid", enterpriseId, "entregador", 0, "attribute") + "}";
    }

    public static String getEnterpriseByDeliverer(String delivererId) throws Exception {
        try{
            if (!UserDataBase.searchBase("id", delivererId, "veiculo", 0, "attribute").isBlank() || !UserDataBase.searchBase("id", delivererId, "placa", 0, "attribute").isBlank());
        }catch (DataNotFoundException e){
            throw new UserIsNotDeliverer();
        }

        return "{"+UserDataBase.searchBase("id", delivererId, "empresas", 0, "attribute") + "}";
    }


    public static void removeOrder(String oid) throws Exception{
        String enterpriseId =OrderDataBase.searchBase("numero", oid, "empresa", 0 ,"attribute");

        try{
            if(!EnterpriseDataBase.searchBase("eid", enterpriseId, "entregador", 0 ,"attribute").equals("[]")){
                String delivererBlock = EnterpriseDataBase.searchBase("eid", enterpriseId, "entregador", 0 ,"attribute");

                delivererBlock = delivererBlock.replace("[","").replace("]","");

                String[] deliverersList = delivererBlock.split(", ");

                for(String delivererEmail : deliverersList){
                    String delivererId = DelivererDataBase.searchBase("email", delivererEmail, "id", 0 ,"attribute");

                    DelivererDataBase.removeOrderForDeliverer(delivererId,oid);
                }

            }
        }catch (DataNotFoundException e){}
    }

}
