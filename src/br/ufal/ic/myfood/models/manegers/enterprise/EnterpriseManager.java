package br.ufal.ic.myfood.models.manegers.enterprise;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.*;
import br.ufal.ic.myfood.exceptions.enterprise.market.InvalidMarketException;
import br.ufal.ic.myfood.exceptions.user.InvalidNameException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.enterprise.market.MarketDataBase;
import br.ufal.ic.myfood.models.data.enterprise.restaurant.RestaurantDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.entity.enterprise.market.Market;
import br.ufal.ic.myfood.models.manegers.enterprise.market.MarketManeger;
import br.ufal.ic.myfood.models.entity.enterprise.restaurant.Restaurant;


import java.util.UUID;

import static java.lang.Integer.parseInt;

public class EnterpriseManager {

    public static String createEnterprise(String enterpriseType, String owner, String name, String address, String thirdParticulars, String secondParticulars, String firstParticulars) throws Exception {
        String eId = UUID.randomUUID().toString();


        checkData(enterpriseType,owner, name, address);

        if(enterpriseType.equals("restaurante")){
            checkRequirement(owner, name, address);
            Restaurant newRestaurant = new Restaurant(enterpriseType,owner,name, address, firstParticulars);
            RestaurantDataBase.addToBase(eId, newRestaurant);
        }
        else if(enterpriseType.equals("mercado")){
            checkHour(thirdParticulars, secondParticulars);
            MarketManeger.checkData(firstParticulars);
            checkRequirement(owner, name, address);
            Market newMarket = new Market(enterpriseType,owner,name, address, thirdParticulars, secondParticulars, firstParticulars);
            MarketDataBase.addToBase(eId, newMarket);
        }
        else if(enterpriseType.equals("farmacia")){
            checkRequirement(owner, name, address);
        }

        return eId;
    }

    public static String createEnterprise(String enterpriseType, String owner, String name, String address,  String secondParticulars, String firstParticulars) throws Exception {
        return createEnterprise(enterpriseType, owner, name, address,null,secondParticulars, firstParticulars);
    }

    public static String createEnterprise(String enterpriseType, String owner, String name, String address, String particulars) throws Exception {
       return createEnterprise(enterpriseType, owner, name, address, null, null, particulars);
    }




    public static String getEnterpriseAttribute(String eid, String attribute) throws Exception{
        try{
            EnterpriseDataBase.searchBase("eid", eid, "eid", 0, "attribute");
        }catch (DataNotFoundException e){
            throw new EnterpriseNotRegisteredException();
        }
        String enterpriseType = EnterpriseDataBase.searchBase("eid", eid, "tipoEmpresa", 0, "attribute");
        String listAttribute = "{[tipoEmpresa], [dono], [nome], [endereco], [abre], [fecha]";
        if(enterpriseType.equals("restaurante")){
            listAttribute += ",[tipoCozinha]}";

            if( attribute == null || attribute.trim().isEmpty() || !listAttribute.contains(attribute)) {
                throw new InvalidEnterpriseAttributeException();
            }
        }if(enterpriseType.equals("mercado")){
            listAttribute += ",[tipoMercado]}";
            if( attribute == null || attribute.trim().isEmpty() || !listAttribute.contains(attribute)) {
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

    private static void checkHour(String openHour, String closeHour) throws Exception{
        if(openHour == null || closeHour == null){
            throw new InvalidHourException();
        }else {
            if(!openHour.matches("\\d{2}\\:\\d{2}") || !closeHour.matches("\\d{2}\\:\\d{2}")){
                throw new InvalidFormatHourException();
            }
        }

        if(parseInt(openHour.substring(0,2)+ openHour.substring(3)) > parseInt(closeHour.substring(0,2)+ closeHour.substring(3)) || parseInt(openHour.substring(0,2)) > 24 || parseInt(closeHour.substring(0,2)) > 24 ){
            throw new InvalidHourException();
        }
    }

    private static void checkData(String enterpriseType, String owner, String name, String address) throws Exception {
        if(name == null || name.equals("")){
            throw new InvalidNameException();
        }

        if(address == null || address.equals("")){
            throw new InvalidEnterpriseAddressException();
        }
        if(enterpriseType == null || enterpriseType.equals("")){
            throw new InvalidEnterpriseTypeException();
        }
    }

    private static void checkRequirement(String owner, String name, String address) throws Exception {
        try{
            if(UserDataBase.searchBase("id", owner, "cpf", 0,"attribute").equals("null")){
                throw new NotOwnerException();
            }
        }catch(DataNotFoundException e){}

        try{
            if(!owner.equals(EnterpriseDataBase.searchBase("nome", name, "dono", 0, "attribute"))){
                throw new NameEnterpriseRegisteredException();
            }
        }catch(DataNotFoundException e){}

        try{
            if(owner.equals(EnterpriseDataBase.searchBase("dono", owner, "dono", 0, "attribute")) && address.equals(EnterpriseDataBase.searchBase("endereco", address, "endereco", 0, "attribute")) && name.equals(EnterpriseDataBase.searchBase("endereco", address, "nome", 0, "attribute"))){
                throw new NameAndAddressRegisteredException();
            }
        }catch(DataNotFoundException e){}
    }


    public static void alterHour(String eid, String openHour, String closeHour) throws Exception{
        checkHour(openHour,closeHour);

            if(!EnterpriseDataBase.searchBase("eid", eid, "tipoEmpresa", 0, "hour").equals("mercado")){
                throw new InvalidMarketException();
            }



        EnterpriseDataBase.editValue(eid, openHour, "abre");
        EnterpriseDataBase.editValue(eid, closeHour, "fecha");
    }
}
