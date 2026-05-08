package br.ufal.ic.myfood.models.data.user.owner;


import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.entity.user.User;
import br.ufal.ic.myfood.models.entity.user.owner.Owner;

public class OwnerDataBase extends UserDataBase {

    public static void addUserBase(String id, Owner userUnregisted) throws Exception{
        String content  = readAll("{\n  \"usuarios\": []\n}", Arquive);

        String newRegister = "    { \"id\": \"" + id + "\", \"nome\": \"" + userUnregisted.getName() + "\", \"email\": \"" + userUnregisted.getEmail() + "\", \"senha\": \"" + userUnregisted.getPassword() + "\", \"endereco\": \"" + userUnregisted.getAddress() + "\", \"cpf\": \"" + userUnregisted.getCpf() +"\" }";

        addToBase(content,newRegister, Arquive);
    }

}
