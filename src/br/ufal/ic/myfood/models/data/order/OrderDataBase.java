package br.ufal.ic.myfood.models.data.order;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.enterprise.IndexMoreExpectedException;
import br.ufal.ic.myfood.exceptions.enterprise.InvalidIndexException;
import br.ufal.ic.myfood.models.data.master.DataBase;
import br.ufal.ic.myfood.models.entity.order.Order;


public class OrderDataBase extends DataBase {
    private static final String Arquive = "data/order/orderDataBase.json";

    public static void createOrderBase(String json) throws Exception{
        if (json.isEmpty()) {
            createDataBase("{\n  \"pedido\": []\n}", Arquive);
        }else{
            createDataBase(json,Arquive);
        }
    }

    public static void addOrderBase(String oid, Order orderUnregisted) throws Exception{
        String content  = readAll("{\n  \"pedido\": []\n}", Arquive);

        String newRegister = "    { \"numero\": \"" + oid + "\", \"cliente\": \"" + orderUnregisted.getUser() + "\", \"empresa\": \"" + orderUnregisted.getEnterprise() + "\", \"valor\": \"" + orderUnregisted.getValue() + "\", \"estado\": \"" + orderUnregisted.getStatus() + "\", \"produtos\": " + orderUnregisted.getProducts() +" }";

        addToBase(content,newRegister, Arquive);
    }

    public static String searchBase(String keyLocation, String key, String resultLocation, int searchStart, String searchType) throws Exception{
        String content = readAll("{\n  \"pedido\": []\n}", Arquive);


        return searchBase(content, keyLocation, key,resultLocation, searchStart, searchType);
    }


    public static void addProductToOrder(String oid, String newProduct) throws Exception{
        String content = readAll("{\n  \"pedido\": []\n}", Arquive);

        String target = "\"" + oid + "\"";
        int indexId = content.indexOf(target);
        if(indexId == -1){
            throw  new DataNotFoundException();
        }

        int beginBlock = content.lastIndexOf("{",  indexId);
        int endBlock = content.indexOf("}",  indexId);
        String block = content.substring(beginBlock, endBlock);

        int indexLocation = block.indexOf("\"" + "produtos"+ "\"");

        int beginBrackets = block.indexOf("[", indexLocation);
        int endBrackets = block.indexOf("]", beginBrackets);

        String insideList = block.substring(beginBrackets+1, endBrackets).trim();

        String newInformation;
        if(insideList.isEmpty()){
            newInformation =  newProduct;
        }else{
            newInformation = ", " + newProduct;
        }


        String newBlock = block.substring(0, endBrackets) + newInformation + block.substring(endBrackets) ;

        String newContent = content.substring(0, beginBlock) + newBlock + content.substring(endBlock);

        createDataBase(newContent,Arquive);
    }


    public static void editValue(String oid, String newValue, String location) throws Exception{
        String content = readAll("{\n  \"pedido\": []\n}", Arquive);

        String target = "\"" + oid + "\"";
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

    public static void removeProductFromOrder(String oid, String product) throws Exception{
        String content = readAll("{\n  \"pedido\": []\n}", Arquive);

        String target = "\"" + oid + "\"";
        int indexId = content.indexOf(target);
        if(indexId == -1){
            throw  new DataNotFoundException();
        }

        int beginBlock = content.lastIndexOf("{",  indexId);
        int endBlock = content.indexOf("}", indexId) + 1;
        String block = content.substring(beginBlock, endBlock);

        int indexLocation = block.indexOf("\"" + "produtos"+ "\"");

        int beginBrackets = block.indexOf("[", indexLocation);
        int endBrackets = block.indexOf("]", beginBrackets);

        String insideList = block.substring(beginBrackets+1, endBrackets).trim();


        int indexItem = insideList.indexOf(product);
        if(indexItem == -1){
            throw  new DataNotFoundException();
        }

        String before = insideList.substring(0, indexItem).stripTrailing();
        String after  = insideList.substring(indexItem + product.length());

        String newList;
        if (before.endsWith(",")) {
            newList = before.substring(0, before.length() - 1) + after;
        } else if (after.stripLeading().startsWith(",")) {
            newList = before + after.stripLeading().substring(2);
        } else {
            newList = "";
        }


        String newBlock = block.substring(0, beginBrackets+1) + newList + block.substring(endBrackets) ;

        String newContent = content.substring(0, beginBlock) + newBlock + content.substring(endBlock);

        createDataBase(newContent,Arquive);
    }

    public static String getOrderNumber(String userId, String enterpriseId, int index) throws Exception {
        int searchStart = 0;

        if (index < 0) {
            throw new InvalidIndexException();
        }

        try {
            searchBase("cliente", userId, "cliente", searchStart, "attribute");
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException();
        }


        try {
            searchBase("empresa", enterpriseId, "empresa", searchStart, "attribute");
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException();
        }

        String check = "";
        int i = 0;
        while (i <= index) {

            try {
                searchBase("cliente", userId, null, searchStart, "cycle");
            } catch (DataNotFoundException e) {
                throw new IndexMoreExpectedException();
            }

            if (userId.equals(searchBase("cliente", userId, "cliente", searchStart, "attribute")) &&
                    enterpriseId.equals(searchBase("cliente", userId, "empresa", searchStart, "attribute"))) {

                if (index != 0 && i != index) {
                    check = searchBase("cliente", userId, null, searchStart, "cycle");
                    searchStart = Integer.parseInt(check);
                } else {
                    check = searchBase("cliente", userId, null, searchStart, "cycle");
                }
                i++;
            } else {
                try {
                    searchStart = Integer.parseInt(searchBase("cliente", userId, null, searchStart, "cycle"));
                } catch (Exception e) {
                    check = "";
                    break;
                }
            }
        }

        if (check.isEmpty()) {
            throw new IndexMoreExpectedException();
        }

        return searchBase("cliente", userId, "numero", searchStart, "attribute");
    }
}
