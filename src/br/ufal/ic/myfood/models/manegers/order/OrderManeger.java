package br.ufal.ic.myfood.models.manegers.order;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.order.*;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.order.OrderDataBase;
import br.ufal.ic.myfood.models.data.product.ProductDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.master.order.Order;


import java.util.UUID;

public class OrderManeger {

    public static String createOrder(String userId, String enterpriseId) throws Exception {
        try{
            if(userId.equals(EnterpriseDataBase.searchBase("eid", enterpriseId, "dono", 0 ,"attribute"))){
                throw new OwnerWithoutPermisssionException();
            }
            if(enterpriseId.equals(OrderDataBase.searchBase("cliente", userId, "empresa", 0 ,"attribute")) && OrderDataBase.searchBase("cliente", userId, "estado", 0 ,"attribute").equals("aberto")){
                throw new OrderUserByEnterpriseRegistraedException();
            }
        }catch(DataNotFoundException e){}

        String oid = UUID.randomUUID().toString();
        OrderDataBase.addOrderBase(oid, new Order(userId,enterpriseId));
        return oid;
    }

    public static void addProductToOrder(String oid, String newProduct) throws Exception{
        try {
            OrderDataBase.searchBase("numero", oid, "numero", 0 , "attribute");
        }catch(DataNotFoundException e){
            throw new OrderNotRegistraedException();
        }

        if(OrderDataBase.searchBase("numero", oid, "estado", 0 ,"attribute").equals("preparando")){
            throw new OrderClosedException();
        }

        String enterpriseId = OrderDataBase.searchBase("numero", oid, "empresa", 0 ,"attribute");

        String productList = ProductDataBase.getProductsByEnterprise(enterpriseId);

        String newProductName = ProductDataBase.searchBase("pid", newProduct, "nome", 0 ,"attribute");
        if(!productList.contains(newProductName)){
            throw new ProductNotRegisteredInEnterpriseException();
        }

        OrderDataBase.addProductToOrder(oid,newProductName);

        Double oldValue = Double.parseDouble(OrderDataBase.searchBase("numero", oid, "valor", 0 , "attribute"));
        Double newValue = Double.parseDouble(ProductDataBase.searchBase("pid", newProduct, "valor", 0 , "attribute"));

        newValue += oldValue;
        newValue = Math.round(newValue * 100.0) / 100.0;
        OrderDataBase.editValue(oid, String.format("%.2f", newValue).replace(",", "."), "valor" );

    }

    public static String getOrder(String oid, String attribute) throws Exception{
        if(attribute == null || attribute.isEmpty()){
            throw new InvalidOrderAttributeException();
        }

        if(attribute.equals("cliente")){
            String userId = OrderDataBase.searchBase("numero", oid, attribute, 0 , "attribute");
            return UserDataBase.searchBase("id", userId, "nome", 0, "attribute");
        }
        else if(attribute.equals("empresa")){
            String enterpriseId = OrderDataBase.searchBase("numero", oid, attribute, 0 , "attribute");
            return EnterpriseDataBase.searchBase("eid", enterpriseId, "nome", 0, "attribute");
        }
        else if(attribute.equals("valor") || attribute.equals("estado") ){
            return OrderDataBase.searchBase("numero", oid, attribute, 0 , "attribute");
        }else if(attribute.equals("produtos")){
            return "{" + OrderDataBase.searchBase("numero", oid, attribute, 0 , "attribute") + "}";
        }
        else {
            throw new AttributeNotFoundException();
        }
    }

    public static void closeOrder(String oid) throws Exception{
        try {
            OrderDataBase.searchBase("numero", oid, "numero", 0 , "attribute");
        }catch(DataNotFoundException e){
            throw new OrderNotFoundException();
        }

        OrderDataBase.editValue(oid,"preparando", "estado" );
    }

    public static void removeProduct(String oid, String product) throws Exception{
        if(product == null || product.isEmpty()){
            throw new InvalidProductException();
        }

        if(!OrderDataBase.searchBase("numero", oid, "estado", 0 ,"attribute").equals("aberto")){
            throw new ProductRemoveInCloseOrderException();
        }

        try{
            OrderDataBase.removeProductFromOrder(oid,product);
        }catch (DataNotFoundException e){
            throw new  ProductNotFoundInOrderException();
        }

        Double oldValue = Double.parseDouble(OrderDataBase.searchBase("numero", oid, "valor", 0 , "attribute"));
        Double newValue = Double.parseDouble(ProductDataBase.searchBase("nome", product, "valor", 0 , "attribute"));

        oldValue -= newValue;
        oldValue = Math.round(oldValue * 100.0) / 100.0;
        OrderDataBase.editValue(oid, String.format("%.2f", oldValue).replace(",", "."), "valor" );



    }
}
