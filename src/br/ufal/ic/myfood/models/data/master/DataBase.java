package br.ufal.ic.myfood.models.data.master;

public class DataBase {
    public static String informationExtraction(String block, String resultLocation) {
        int indexLocation = block.indexOf("\"" + resultLocation + "\"");
        if(indexLocation == -1){
            return "";
        }
        int after = block.indexOf(":", indexLocation)+1;
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
