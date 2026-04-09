package br.ufal.ic.myfood.models.user;

import br.ufal.ic.myfood.exceptions.UserNotRegisteredException;

import java.io.*;
import java.nio.file.*;

public class UserDataBase {

    private static final String Arquive = "data/user/userDataBase.json";

    public static void createUserBase(String json) throws Exception{
        File f = new File(Arquive);

        File pasta = f.getParentFile();
            if (pasta != null && !pasta.exists()) {
                pasta.mkdirs();
            }

        FileWriter writer  = new FileWriter(Arquive);
        writer.write(json);
        writer.close();
    }

    public static String readAll() throws Exception{
        File f = new File(Arquive);

        File pasta = f.getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        if (!f.exists() || f.length() == 0) {
            createUserBase("{\n  \"usuarios\": []\n}");
        }

        return new String(Files.readAllBytes(Paths.get(Arquive)));

    }

    public static void addUserBase(String id, User userUnregisted) throws Exception{
        String content  = readAll();

        String newRegister = "    { \"id\": \"" + id + "\", \"nome\": \"" + userUnregisted.nome + "\", \"email\": \"" + userUnregisted.email + "\", \"senha\": \"" + userUnregisted.senha + "\", \"endereco\": \"" + userUnregisted.endereco + "\", \"cpf\": \"" + userUnregisted.cpf +"\" }";

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

        createUserBase(newContent);
    }

    public static void removeUserBase(String id) throws Exception{
        String content  = readAll();

        String target = "\"" + id + "\"";
        int indexId = content.indexOf(target);
        if(indexId != -1){
            throw new UserNotRegisteredException();
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

        createUserBase(before + after);
    }

    public static String searchBase(String keyLocation, String key, String resultLocation) throws Exception{
        String content = readAll();

        String target = "\"" + keyLocation + "\": \"" + key + "\"";
        int index = content.indexOf(target);

        if(index == -1){
            throw new UserNotRegisteredException();
        }

        int beginBlock = content.lastIndexOf("{",  index);
        int endBlock = content.indexOf("}",  index);
        String block = content.substring(beginBlock, endBlock);

        int indexLocation = block.indexOf("\"" + resultLocation + "\"");
        if(indexLocation == -1){
            throw new UserNotRegisteredException();
        }

        int after = block.indexOf(":",  indexLocation)+1;
        while(block.charAt(after) == ' ') after++;

        if(block.charAt(after) == '"'){
            int begin = after + 1;
            int end = block.indexOf("\"", begin);
            return block.substring(begin, end);
        }

        int end = block.indexOf(",",  after);
        if(end == -1)end = block.length();

        return block.substring(after, end).trim();
    }

}
