package br.ufal.ic.myfood.models.data.enterprise.market;



import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.entity.enterprise.market.Market;
import br.ufal.ic.myfood.models.entity.enterprise.restaurant.Restaurant;

public class MarketDataBase extends EnterpriseDataBase {


    public static void addToBase(String eid, Market marketUnregisted) throws Exception{
        String content  = EntepriseReadAll();

        String newRegister = "    { \"eid\": \"" + eid + "\", \"tipoEmpresa\": \"" + marketUnregisted.getEnterpriseType() + "\", \"dono\": \"" + marketUnregisted.getOwner() + "\", \"nome\": \"" + marketUnregisted.getName() + "\", \"endereco\": \"" + marketUnregisted.getAddress() + "\", \"abre\": \"" + marketUnregisted.getOpenHour() +  "\", \"fecha\": \"" + marketUnregisted.getClouseHour() +"\", \"tipoMercado\": \"" + marketUnregisted.getMarketType() +"\" }";

        addToBase(content,newRegister, EnterpriseDataBase.getArquive());
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = EntepriseReadAll();

        removeFromBase(id, content,EnterpriseDataBase.getArquive());
    }



}
