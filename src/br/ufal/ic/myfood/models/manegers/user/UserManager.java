package br.ufal.ic.myfood.models.manegers.user;

import br.ufal.ic.myfood.exceptions.user.*;
import br.ufal.ic.myfood.models.data.user.UserDataBase;
import br.ufal.ic.myfood.models.master.user.User;

import java.util.UUID;

public class UserManager {
    private String id;

    public UserManager(String name, String email, String password, String address, String cpf) throws Exception {
        checkData(name, email, password, address, cpf);
        if(cpf == null || cpf.isBlank()){
            throw new InvalidCpfFormatException();
        }
        criarUsuario(name, email, password, address, cpf);
    }
    public UserManager(String name, String email, String password, String address) throws Exception {
        checkData(name, email, password, address, null);
        criarUsuario(name, email, password, address, null);


    }

    public static String getAtributoUsuario (String id, String atributo) throws Exception{
        return UserDataBase.searchBase("id", id, atributo);
    }

    public void criarUsuario(String name, String email, String password, String address,String cpf) throws Exception{
        try{
            UserDataBase.searchBase("email", email,"email");
            throw new EmailRegisteredException();

        } catch (UserNotRegisteredException e){
            this.id = UUID.randomUUID().toString();
            User newUser = new User(name, email, password, address, cpf);
            UserDataBase.addUserBase(id, newUser);
        }
    }


    public static String login(String email, String password) throws Exception {
        if(email == null || email.isBlank()){
            throw new InvalidLoginExcepion();
        }else {
            if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}(\\.[a-z]{2,3})?$")){
                throw new InvalidLoginExcepion();
            }
        }

        if(password == null || password.isBlank()){
            throw new InvalidLoginExcepion();
        }

        if(!password.equals(UserDataBase.searchBase("email", email, "senha"))){
            throw new InvalidLoginExcepion();
        }

        return UserDataBase.searchBase("email", email, "id");
    }

    private void checkData(String name, String email, String password, String address, String cpf) throws Exception {
        if(cpf != null){
            if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
                throw new InvalidCpfFormatException();
            }
        }

        if(name == null || name.isBlank()){
            throw new InvalidNameException();
        }

        if(email == null || email.isBlank()){
            throw new InvalidEmailException();
        }else {
            if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}(\\.[a-z]{2,3})?$")){
                throw new InvalidEmailException();
            }
        }

        if(password == null || password.isBlank()){
            throw new InvalidPasswordException();
        }

        if(address == null || address.isBlank()){
            throw new InvalidAddressException();
        }
    }
}
