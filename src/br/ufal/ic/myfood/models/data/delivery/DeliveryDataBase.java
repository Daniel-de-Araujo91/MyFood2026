package br.ufal.ic.myfood.models.data.delivery;



import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.entity.delivery.Delivery;


public class DeliveryDataBase extends DataBase {
    private static final String Arquive = "data/delivery/deliveryDataBase.json";

    public static void createDeliveryBase(String json) throws Exception{
        if (json.isEmpty()) {
            createDataBase("{\n  \"entrega\": []\n}", Arquive);
        }else{
            createDataBase(json,Arquive);
        }
    }

    public static void addToBase(String en, Delivery deliveryUnregisted) throws Exception{
        String content  = readAll("{\n  \"entrega\": []\n}", Arquive);

        String newRegister = "    { \"en\": \"" + en + "\", \"pedido\": \"" + deliveryUnregisted.getOrderId() + "\", \"destino\": \"" + deliveryUnregisted.getDestination() + "\", \"cliente\": \"" + deliveryUnregisted.getUserName() + "\", \"entregador\": \"" + deliveryUnregisted.getDelivererName() + "\", \"empresa\": \"" + deliveryUnregisted.getEnterpriseName() +  "\", \"estado\": \"" + "ativo" +  "\", \"produtos\": \"" + deliveryUnregisted.getProducts() +"\" }";

        addToBase(content,newRegister, Arquive);
    }

    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll("{\n  \"entrega\": []\n}", Arquive);


        return searchBase(content, keyLocation, key,resultLocation, searchStart, searchType);
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = readAll("{\n  \"entrega\": []\n}", Arquive);

        removeFromBase(id, content,EnterpriseDataBase.getArquive());
    }

    public static void editValue(String en, String newValue, String location) throws Exception{
        String content =readAll("{\n  \"entrega\": []\n}", Arquive);

        String target = "\"" + en + "\"";
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



}
