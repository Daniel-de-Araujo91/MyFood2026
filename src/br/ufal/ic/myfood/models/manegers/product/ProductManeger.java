package br.ufal.ic.myfood.models.manegers.product;

import br.ufal.ic.myfood.exceptions.data.DataNotFoundException;
import br.ufal.ic.myfood.exceptions.product.*;
import br.ufal.ic.myfood.exceptions.user.InvalidNameException;
import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.product.ProductDataBase;
import br.ufal.ic.myfood.models.entity.product.Product;

import java.util.UUID;

public class ProductManeger {
    public static String createProduct(String enterpriseId, String name, String value, String category) throws Exception {
        try {
            if(enterpriseId.equals(ProductDataBase.searchBase("nome",name,"empresa", 0,"attribute"))){
                throw new ProductNameRegisteredException();
            }
        }catch(DataNotFoundException e){}

        checkData(name, value, category);
        String pId = UUID.randomUUID().toString();
        ProductDataBase.addToBase(pId, new Product(enterpriseId, name,value,category));

        return pId;
    }

    private static void checkData(String name, String value, String category) throws Exception {

        if(name == null || name.isBlank()){
            throw new InvalidNameException();
        }

        if(category == null || category.isBlank()){
            throw new InvalidCategoryException();
        }

        if(Double.parseDouble(value) < 0){
            throw new InvalidValueException();
        }
    }

    public static void editProduct(String pid, String name, String value, String category) throws Exception {
        try {
            if (pid.equals(ProductDataBase.searchBase("pid", pid, "pid", 0,"attribute"))) {
                checkData(name,value,category);
                ProductDataBase.editValue(pid, name, "nome");
                ProductDataBase.editValue(pid, value, "valor");
                ProductDataBase.editValue(pid, category, "categoria");
            }
        }catch(DataNotFoundException e){
            throw new ProductNotRegisteredException();
        }
    }

    public static String getProduct(String name, String enterpriseId, String attribute) throws Exception {
        String listAttribute = "{[empresa], [nome], [valor], [categoria]}";
        if(!listAttribute.contains(attribute)){
            throw new InvalidProductAttributeException();
        }

        try{
            if(enterpriseId.equals(ProductDataBase.searchBase("nome",name,"empresa", 0,"attribute")) && name.equals(ProductDataBase.searchBase("empresa",enterpriseId,"nome", 0,"attribute")) ){
            if(attribute.equals("empresa")){
                return EnterpriseDataBase.searchBase("eid", enterpriseId, "nome", 0, "attribute");
            }
                return ProductDataBase.searchBase("nome",name,attribute, 0,"attribute");
            }
        }catch(DataNotFoundException e){
            throw new ProductNotFoundException();
        }

        return "";
    }


}
