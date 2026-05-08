package br.ufal.ic.myfood.models.data.user.deliverer;


import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.entity.user.deliverer.Deliverer;

public class DelivererDataBase extends UserDataBase {

    public static void addUserBase(String id, Deliverer delivererUnregisted) throws Exception{
        String content  = readAll("{\n  \"usuarios\": []\n}", Arquive);

        String newRegister = "    { \"id\": \"" + id + "\", \"nome\": \"" + delivererUnregisted.getName() + "\", \"email\": \"" + delivererUnregisted.getEmail() + "\", \"senha\": \"" + delivererUnregisted.getPassword() + "\", \"endereco\": \"" + delivererUnregisted.getAddress() + "\", \"veiculo\": \"" + delivererUnregisted.getVehicle()+ "\", \"placa\": \"" + delivererUnregisted.getPlate()+"\", \"empresas\": \"" + delivererUnregisted.getEnterprises() +"\", \"pedidos\": \"" + delivererUnregisted.getOrder()+"\", \"pedidosFarmacia\": \"" + delivererUnregisted.getOrder()+ "\" }";

        addToBase(content,newRegister, Arquive);
    }


    public static void editDelivererDataBase(String id, String newData, String location) throws Exception{
        String content = readAll("{\n  \"usuarios\": []\n}", Arquive);

        String target = "\"" + id + "\"";
        int indexId = content.indexOf(target);
        if(indexId == -1){
            throw  new DataNotFoundException();
        }

        int beginBlock = content.lastIndexOf("{",  indexId);
        int endBlock = content.indexOf("}",  indexId) +1;
        String block = content.substring(beginBlock, endBlock);

        int indexLocation = block.indexOf("\"" + location + "\"");

        int beginBrackets = block.indexOf("[", indexLocation);
        int endBrackets;

        if(location.equals("empresas")){
            endBrackets = block.lastIndexOf("]",  block.indexOf("\"" + "pedidos" + "\""));
        }
        else if( location.equals("pedidos"))
        {
            endBrackets = block.lastIndexOf("]",  block.indexOf("\"" + "pedidosFarmacia" + "\""));
        }else{
            endBrackets = block.indexOf("]", beginBrackets);
        }


        String insideList = block.substring(beginBrackets+1, endBrackets).trim();

        String newInformation;
        if(insideList.isEmpty()){
            newInformation = newData;
        }else{
            newInformation = ", " + newData;
        }


        String newBlock = block.substring(0, endBrackets) + newInformation + block.substring(endBrackets);

        String newContent = content.substring(0, beginBlock) + newBlock + content.substring(endBlock);

        createDataBase(newContent,Arquive);
    }

    public static void removeOrderForDeliverer(String delivererId, String order) throws Exception{
        String content = readAll("{\n  \"usuarios\": []\n}", Arquive);

        String target = "\"" + delivererId + "\"";
        int indexId = content.indexOf(target);
        if(indexId == -1){
            throw  new DataNotFoundException();
        }

        int beginBlock = content.lastIndexOf("{",  indexId);
        int endBlock = content.indexOf("}", indexId) + 1;
        String block = content.substring(beginBlock, endBlock);

        int indexLocation;
        if(DelivererDataBase.searchBase( "id", delivererId, "pedidosFarmacia", 0, "attribute").equals("[]")){
            indexLocation = block.indexOf("\"" + "pedidos"+ "\"");
        }
        else{
            indexLocation = block.indexOf("\"" + "pedidosFarmacia"+ "\"");
        }


        int beginBrackets = block.indexOf("[", indexLocation);
        int endBrackets = block.indexOf("]", beginBrackets);

        String insideList = block.substring(beginBrackets+1, endBrackets).trim();


        int indexItem = insideList.indexOf(order);
        if(indexItem == -1){
            throw  new DataNotFoundException();
        }

        String before = insideList.substring(0, indexItem).stripTrailing();
        String after  = insideList.substring(indexItem + order.length());

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


}
