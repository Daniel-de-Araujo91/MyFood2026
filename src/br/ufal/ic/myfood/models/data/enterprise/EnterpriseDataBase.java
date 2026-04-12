package br.ufal.ic.myfood.models.data.enterprise;



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

    public static void createEnterpriseBase(String json) throws Exception{
        File f = new File(Arquive);

        File pasta = f.getParentFile();
            if ((pasta != null && !pasta.exists()) || json.isEmpty()) {
                assert pasta != null;
                pasta.mkdirs();
                createEnterpriseBase("{\n  \"empresas\": []\n}");
            }else{
                FileWriter writer  = new FileWriter(Arquive);
                writer.write(json);
                writer.close();
            }


    }

    public static String readAll() throws Exception{
        File f = new File(Arquive);

        File pasta = f.getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        if (!f.exists() || f.length() == 0) {
            createEnterpriseBase("{\n  \"empresas\": []\n}");
        }

        return new String(Files.readAllBytes(Paths.get(Arquive)));

    }



    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll();

        String target = "\"" + keyLocation + "\": \"" + key + "\"";
        int index = content.indexOf(target, searchStart);

        if(index == -1){
            return "";
        }

        int beginBlock = content.lastIndexOf("{",  index);
        int endBlock = content.indexOf("}",  index);
        String block = content.substring(beginBlock, endBlock);

        if(searchType.equals("block")){
            return block;
        }
        if(searchType.equals("cycle")){
            return String.valueOf(endBlock);
        }

        return informationExtraction(block, resultLocation);
    }



    public static String getEnterpriseByUser(String id) throws Exception {
        if(UserDataBase.searchBase("id", id, "cpf").equals("null")){
            throw new NotOwnerException();
        }

        String result ="{[";
        int searchStart = 0;
        while(true){
          String block  = searchBase("dono", id, null, searchStart,"block");
          if(block.isEmpty()){
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

        if(searchBase("nome", name , "nome", searchStart,"attribute").isEmpty()){
            throw new EnterpriseNameNotExistException();
        }

        if(index < 0){
            throw new InvalidIndexException();
        }

        String check = "";
        int i = 0;
        while(i <= index){
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
