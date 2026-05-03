package br.ufal.ic.myfood.models.data.enterprise.pharmacy;



import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.entity.enterprise.market.Market;
import br.ufal.ic.myfood.models.entity.enterprise.pharmacy.Pharmacy;

public class PharmacyDataBase extends EnterpriseDataBase {


    public static void addToBase(String eid, Pharmacy pharmacyUnregisted) throws Exception{
        String content  = EntepriseReadAll();

        String newRegister = "    { \"eid\": \"" + eid + "\", \"tipoEmpresa\": \"" + pharmacyUnregisted.getEnterpriseType() + "\", \"dono\": \"" + pharmacyUnregisted.getOwner() + "\", \"nome\": \"" + pharmacyUnregisted.getName() + "\", \"endereco\": \"" + pharmacyUnregisted.getAddress() + "\", \"aberto24Horas\": \"" + pharmacyUnregisted.getOpen24h() +  "\", \"numeroFuncionarios\": \"" + pharmacyUnregisted.getNumberOfEmployees()+"\" }";

        addToBase(content,newRegister, EnterpriseDataBase.getArquive());
    }

    public static void removeFromBase(String id) throws Exception{
        String content  = EntepriseReadAll();

        removeFromBase(id, content,EnterpriseDataBase.getArquive());
    }



}
