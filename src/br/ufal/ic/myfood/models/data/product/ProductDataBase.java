package br.ufal.ic.myfood.models.data.product;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.EnterpriseNotFoundException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.entity.product.Product;


public class ProductDataBase extends DataBase {

    private static final String Arquive = "data/product/productDataBase.json";

    public static void createProductBase(String json) throws Exception{
        if (json.isEmpty()) {
            createDataBase("{\n  \"produto\": []\n}", Arquive);
        }else{
           createDataBase(json,Arquive);
        }
    }

    public static void addToBase(String pid, Product productUnregisted) throws Exception{
        String content  = readAll("{\n  \"produto\": []\n}", Arquive);

        String newRegister = "    { \"pid\": \"" + pid + "\", \"empresa\": \"" + productUnregisted.getEnterpriseId() + "\", \"nome\": \"" + productUnregisted.getName() + "\", \"valor\": \"" + productUnregisted.getValue() + "\", \"categoria\": \"" + productUnregisted.getCategory() +"\" }";

        addToBase(content,newRegister, Arquive);
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = readAll("{\n  \"produto\": []\n}", Arquive);

        removeFromBase(id, content,Arquive);
    }

    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll("{\n  \"produto\": []\n}", Arquive);


        return searchBase(content, keyLocation, key,resultLocation, searchStart, searchType);
    }

    public static void editValue(String pid, String newValue, String location) throws Exception{
        String content = readAll("{\n  \"produto\": []\n}", Arquive);

        String target = "\"" + pid + "\"";
        int indexId = content.indexOf(target);
        if(indexId == -1){
            throw  new DataNotFoundException();
        }

        int beginBlock = content.lastIndexOf("{",  indexId);
        int endBlock = content.indexOf("}",  indexId);
        String block = content.substring(beginBlock, endBlock);

        int indexLocation = block.indexOf("\"" + location+ "\"");
        if(informationExtraction(block,location).isEmpty()){
            throw new DataNotFoundException();
        }

        int after = block.indexOf(":", indexLocation)+1;
        while(block.charAt(after) == ' ') after++;


        int begin = after + 1;
        int end = block.indexOf("\"", begin);
        String value = "\"" + newValue + "\"";


       String newBlock = block.substring(0, begin) + newValue + block.substring(end) ;

       String newContent = content.substring(0, beginBlock) + newBlock + content.substring(endBlock);

       createDataBase(newContent,Arquive);
    }

    public static String getProductsByEnterprise(String enterpriseId) throws Exception {
        try {
            EnterpriseDataBase.searchBase("eid", enterpriseId, "eid", 0, "attribute");
        }catch(DataNotFoundException e){
            throw new EnterpriseNotFoundException();
        }

        int searchStart = 0;
        String result ="{[";
        String block = "";
        while(true){
            try{
                block = searchBase("empresa", enterpriseId, null, searchStart,"block");
            }catch (DataNotFoundException e){
                break;
            }


            result +=   informationExtraction(block, "nome") +", ";


            searchStart = Integer.parseInt(searchBase("empresa", enterpriseId, null, searchStart, "cycle"));
        }

        if(searchStart != 0){
            result = result.substring(0, result.lastIndexOf(",")) + "]}";
        } else {
            result += "]}";
        }

        return result;
    }
}
