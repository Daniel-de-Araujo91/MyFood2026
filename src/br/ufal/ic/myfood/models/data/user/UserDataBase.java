package br.ufal.ic.myfood.models.data.user;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.user.UserNotRegisteredException;
import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.master.user.User;

import java.io.*;
import java.nio.file.*;

public class UserDataBase extends DataBase {

    private static final String Arquive = "data/user/userDataBase.json";

    public static void createUserBase(String json) throws Exception{
            if ( json.isEmpty()) {
                createDataBase("{\n  \"usuarios\": []\n}", Arquive);
            }else{
                createDataBase(json, Arquive);
            }
    }



    public static void addUserBase(String id, User userUnregisted) throws Exception{
        String content  = readAll("{\n  \"usuarios\": []\n}", Arquive);

        String newRegister = "    { \"id\": \"" + id + "\", \"nome\": \"" + userUnregisted.getName() + "\", \"email\": \"" + userUnregisted.getEmail() + "\", \"senha\": \"" + userUnregisted.getPassword() + "\", \"endereco\": \"" + userUnregisted.getAddress() + "\", \"cpf\": \"" + userUnregisted.getCpf() +"\" }";

        addToBase(content,newRegister, Arquive);
    }

    public static void removeUserBase(String id) throws Exception{
        String content  = readAll("{\n  \"usuarios\": []\n}", Arquive);

        removeFromBase(id, content,  Arquive);
    }

    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll("{\n  \"usuarios\": []\n}", Arquive);

        return searchBase(content, keyLocation, key,resultLocation, searchStart, searchType);
    }

}
