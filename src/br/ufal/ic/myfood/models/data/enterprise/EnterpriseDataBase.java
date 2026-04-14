package br.ufal.ic.myfood.models.data.enterprise;



import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.EnterpriseNameNotExistException;
import br.ufal.ic.myfood.exceptions.enterprise.IndexMoreExpectedException;
import br.ufal.ic.myfood.exceptions.enterprise.InvalidIndexException;
import br.ufal.ic.myfood.exceptions.enterprise.NotOwnerException;
import br.ufal.ic.myfood.exceptions.user.InvalidNameException;
import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EnterpriseDataBase extends DataBase {

    private static final String Arquive = "data/enterprise/enterpriseDataBase.json";

    public static String getArquive() {
        return Arquive;
    }

    public static void createEnterpriseBase(String json) throws Exception{
            if (json.isEmpty()) {
                createDataBase("{\n  \"empresas\": []\n}", Arquive);
            }else{
                createDataBase(json, Arquive);
            }
    }

    public static String EntepriseReadAll() throws Exception{
        return readAll("{\n  \"empresas\": []\n}", Arquive);
    }



    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll("{\n  \"empresas\": []\n}", Arquive);

        return searchBase(content, keyLocation, key, resultLocation, searchStart, searchType);
    }



    public static String getEnterpriseByUser(String id) throws Exception {
        if(UserDataBase.searchBase("id", id, "cpf", 0, "attribute").equals("null")){
            throw new NotOwnerException();
        }

        String result ="{[";
        int searchStart = 0;
        String block = "";
        while(true){
        try {
            block = searchBase("dono", id, null, searchStart, "block");
        }catch (DataNotFoundException e){
            break;
        }
          result +=  "[" + informationExtraction(block, "nome")+", ";
          result += informationExtraction(block, "endereco") +"], ";

          searchStart = Integer.parseInt(searchBase("dono", id, null, searchStart, "cycle"));
        }
        if(searchStart != 0){
            result = result.substring(0, result.lastIndexOf(",")) + "]}";
        } else {
            result += "]}";
        }

        return result;
    }

    public static String getIdEnterprise(String owner, String name, int index) throws Exception {
        int searchStart = 0;

        if(name == null || name.isBlank()){
            throw new InvalidNameException();
        }
        try{
            searchBase("nome", name , "nome", searchStart,"attribute");
        }catch (DataNotFoundException e){
            throw new EnterpriseNameNotExistException();
        }

        if(index < 0){
            throw new InvalidIndexException();
        }

        String check = "";
        int i = 0;
        while(i <= index){

            try {
                searchBase("dono", owner, null, searchStart, "cycle");
            }catch (DataNotFoundException e){
                throw new IndexMoreExpectedException();
            }

            if(owner.equals(searchBase("dono", owner, "dono", searchStart, "attribute")) && name.equals(searchBase("dono", owner, "nome", searchStart, "attribute"))){

                if(index != 0 && i != index){
                    check = searchBase("dono", owner, null, searchStart, "cycle");
                    searchStart = Integer.parseInt(check);
                }else{
                    check = searchBase("dono", owner, null, searchStart, "cycle");
                }
                    i++;
            }else{
                try{
                    searchStart = Integer.parseInt(searchBase("dono", owner, null, searchStart, "cycle"));
                }catch (Exception e){
                    check ="";
                    break;
                }

            }

        }

        if(check.isEmpty() )  {
            throw new IndexMoreExpectedException();
        }


        String teste = searchBase("dono", owner, "eid", searchStart, "attribute");
        return searchBase("dono", owner, "eid", searchStart, "attribute");

    }

}
