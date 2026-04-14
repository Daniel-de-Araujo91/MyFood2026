package br.ufal.ic.myfood.models.manegers.enterprise.restaurant;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.NameAndAddressRegisteredException;
import br.ufal.ic.myfood.exceptions.enterprise.NameEnterpriseRegisteredException;
import br.ufal.ic.myfood.exceptions.enterprise.NotOwnerException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;

public class RestaurantManeger {


    public static void checkData(String owner, String name, String address, String kitchenType) throws Exception {
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
}
