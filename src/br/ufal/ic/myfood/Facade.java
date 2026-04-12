package br.ufal.ic.myfood;

import br.ufal.ic.myfood.models.data.enterprise.EnterpriseDataBase;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.manegers.enterprise.EnterpriseManager;
import br.ufal.ic.myfood.models.manegers.user.UserManager;

public class Facade {

    public void zerarSistema() throws Exception {
        UserDataBase.createUserBase("");
        EnterpriseDataBase.createEnterpriseBase("");
    }

    public String getAtributoUsuario(String id , String atributo) throws Exception {
       return UserManager.getAtributoUsuario(id, atributo);
    }

    public void criarUsuario(String name, String email, String senha, String endereco) throws Exception {
        new UserManager(name, email, senha, endereco);
    }
    public void criarUsuario(String name, String email, String senha, String endereco, String cpf) throws Exception {
        new UserManager(name, email, senha, endereco, cpf);
    }

    public String login(String email, String senha) throws Exception {
        return UserManager.login(email, senha);
    }

    public String criarEmpresa(String enterpriseType, String owner, String name, String address, String particulars) throws Exception {
        return EnterpriseManager.createEnterprise(enterpriseType,owner,name, address, particulars);
    }

    public String getEmpresasDoUsuario (String idOwner) throws Exception {
        return EnterpriseDataBase.getEnterpriseByUser(idOwner);
    }

    public String getAtributoEmpresa(String eid, String atributo) throws Exception {
        return EnterpriseManager.getEnterpriseAttribute(eid, atributo);
    }

    public String getIdEmpresa(String owner, String name, int index) throws Exception {
        return EnterpriseDataBase.getIdEnterprise(owner,name,index);
    }
    public void encerrarSistema(){}

}
