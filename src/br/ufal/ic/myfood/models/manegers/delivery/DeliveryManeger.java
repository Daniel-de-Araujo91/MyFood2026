package br.ufal.ic.myfood.models.manegers.delivery;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.delivery.DelivererInRouteException;
import br.ufal.ic.myfood.exceptions.delivery.DeliveryByOrderNotFoundException;
import br.ufal.ic.myfood.exceptions.delivery.DeliveryNotFoundException;
import br.ufal.ic.myfood.exceptions.delivery.OrderIsNotDone;
import br.ufal.ic.myfood.exceptions.order.*;
import br.ufal.ic.myfood.exceptions.user.deliverer.InvalidDelivererException;
import br.ufal.ic.myfood.models.data.delivery.DeliveryDataBase;
import br.ufal.ic.myfood.models.data.order.OrderDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.data.user.deliverer.DelivererDataBase;
import br.ufal.ic.myfood.models.entity.delivery.Delivery;
import br.ufal.ic.myfood.models.manegers.user.deliverer.DelivererManager;

import java.util.UUID;

public class DeliveryManeger {

    public static String createDelivery(String orderId, String delivererId, String destination) throws Exception {

        if(!OrderDataBase.searchBase("numero", orderId, "estado", 0, "attribute").equals("pronto")){
            throw new OrderIsNotDone();
        }

        try {
            String delivererName = UserDataBase.searchBase("id", delivererId, "nome", 0, "attribute");
            if (DeliveryDataBase.searchBase("entregador", delivererName, "entregador", 0, "attribute").equals(UserDataBase.searchBase("id", delivererId, "nome", 0, "attribute")) && DeliveryDataBase.searchBase("entregador", delivererName, "estado", 0, "attribute").equals("ativo")) {
                throw new DelivererInRouteException();
            }
        }catch (DataNotFoundException e){}

        try{
            if (!UserDataBase.searchBase("id", delivererId, "veiculo", 0, "attribute").isBlank() || !UserDataBase.searchBase("id", delivererId, "placa", 0, "attribute").isBlank());
        }catch (DataNotFoundException e){
            throw new InvalidDelivererException();
        }


        String en = UUID.randomUUID().toString();
        DeliveryDataBase.addToBase(en, new Delivery(orderId, delivererId, destination));

        OrderDataBase.editValue(orderId,"entregando", "estado");
        DelivererManager.removeOrder(orderId);
        return en;
    }

    public static String getAttributeDelivery (String en, String attribute) throws Exception{
       if(attribute == null || attribute.equals("")){
           throw new InvalidOrderAttributeException();
       }

       try{
           if(attribute.equals("empresaen")){
               attribute = "empresa";
           }
           if(attribute.equals("produtos")){
               return "{" + DeliveryDataBase.searchBase("en",en, attribute, 0, "attribute") + "}";
           }
           return DeliveryDataBase.searchBase("en",en, attribute, 0, "attribute");
       }catch (DataNotFoundException e){
           throw new AttributeNotFoundException();
       }

    }

    public static String getIdDelivery(String oid) throws Exception{
        try{
            return DeliveryDataBase.searchBase("pedido", oid, "en", 0, "attribute");
        }catch (DataNotFoundException e){
            throw new DeliveryByOrderNotFoundException();
        }
    }

    public static void delivery(String en) throws Exception {
        try{
            DeliveryDataBase.searchBase("en", en,"en", 0, "attribute");
        }catch (DataNotFoundException e){
            throw new DeliveryNotFoundException();
        }

        String orderId =  DeliveryDataBase.searchBase("en", en,"pedido", 0, "attribute");
        OrderDataBase.editValue(orderId,"entregue", "estado");
        DeliveryDataBase.editValue(en,"entregue", "estado");
    }


}
