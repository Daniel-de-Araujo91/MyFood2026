package br.ufal.ic.myfood.models.data.master;




import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.NameEnterpriseRegisteredException;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DataBase {

    public static void createDataBase(String json, String Arquive) throws Exception {
        File f = new File(Arquive);

        File pasta = f.getParentFile();
        if ((pasta != null && !pasta.exists())) {
            assert pasta != null;
            pasta.mkdirs();
        }

        FileWriter writer  = new FileWriter(Arquive);
        writer.write(json);
        writer.close();
    }

    public static String readAll(String json, String Arquive) throws Exception{
        File f = new File(Arquive);

        File pasta = f.getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        if (!f.exists() || f.length() == 0) {
            createDataBase(json, Arquive);
        }

        return new String(Files.readAllBytes(Paths.get(Arquive)));

    }

    public static String informationExtraction(String block, String resultLocation) {
        int indexLocation = block.indexOf("\"" + resultLocation + "\"");
        if(indexLocation == -1){
            return "";
        }
        int after = block.indexOf(":", indexLocation) +1;
        while(block.charAt(after) == ' ') after++;

        if(block.charAt(after) == '"'){
            int begin = after + 1;
            int end = block.indexOf("\"", begin);
            return block.substring(begin, end);
        }

        if (block.charAt(after) == '[') {
            int end = block.indexOf("]", after) + 1;
            return block.substring(after, end);
        }

        int end = block.indexOf(",",  after);
        if(end == -1)end = block.length();

        return block.substring(after, end).trim();
    }

    public static void addToBase(String content, String newRegister, String Arquive) throws Exception{

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

        createDataBase(newContent, Arquive);
    }

    public static void removeFromBase(String id, String content, String Arquive) throws Exception{


        String target = "\"" + id + "\"";
        int indexId = content.indexOf(target);
        if(indexId != -1){
            throw  new DataNotFoundException();
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

        createDataBase(before + after, Arquive);
    }


    public static String searchBase( String content, String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{


        String target = "\"" + keyLocation + "\": \"" + key + "\"";
        int index = content.indexOf(target, searchStart);

        if(index == -1){
            throw  new DataNotFoundException();
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
}
