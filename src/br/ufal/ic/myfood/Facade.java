package br.ufal.ic.myfood;

import br.ufal.ic.myfood.exceptions.UserNotRegisteredException;
import br.ufal.ic.myfood.models.user.UserDataBase;
import br.ufal.ic.myfood.models.user.UserManager;

public class Facade {

    public void zerarSistema() throws Exception {
        UserDataBase.createUserBase("");
    }

    public String getAtributoUsuario(String id , String atributo) throws Exception {
       return UserManager.getAtributoUsuario(id, atributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        new UserManager(nome, email, senha, endereco);
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        new UserManager(nome, email, senha, endereco, cpf);
    }


    public String login(String email, String senha) throws Exception {
        return UserManager.login(email, senha);
    }

    public void encerrarSistema(){}

}
