package br.ufal.ic.myfood.models.user;

import br.ufal.ic.myfood.exceptions.*;

import java.util.UUID;

public class UserManager {
    private String id;

    public UserManager(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        checkData(nome, email, senha, endereco, cpf);
        if(cpf == null || cpf.isBlank()){
            throw new InvalidCpfFormatException();
        }
        criarUsuario(nome, email, senha, endereco, cpf);
    }
    public UserManager(String nome, String email, String senha, String endereco) throws Exception {
        checkData(nome, email, senha, endereco, null);
        criarUsuario(nome, email, senha, endereco, null);
    }

    public static String getAtributoUsuario (String id, String atributo) throws Exception{
        return UserDataBase.searchBase("id", id, atributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco,String cpf) throws Exception{
        try{
            UserDataBase.searchBase("email", email,"email");
            throw new EmailRegisteredException();

        } catch (UserNotRegisteredException e){
            this.id = UUID.randomUUID().toString();
            User newUser = new User(nome, email, senha, endereco, cpf);
            UserDataBase.addUserBase(id, newUser);
        }
    }


    public static String login(String email, String senha) throws Exception {
        if(email == null || email.isBlank()){
            throw new InvalidLoginExcepion();
        }else {
            if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}(\\.[a-z]{2,3})?$")){
                throw new InvalidLoginExcepion();
            }
        }

        if(senha == null || senha.isBlank()){
            throw new InvalidLoginExcepion();
        }

        if(!senha.equals(UserDataBase.searchBase("email", email, "senha"))){
            throw new InvalidLoginExcepion();
        }

        return UserDataBase.searchBase("email", email, "id");
    }

    private void checkData(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if(cpf != null){
            if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
                throw new InvalidCpfFormatException();
            }
        }

        if(nome == null || nome.isBlank()){
            throw new InvalidNameException();
        }

        if(email == null || email.isBlank()){
            throw new InvalidEmailException();
        }else {
            if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}(\\.[a-z]{2,3})?$")){
                throw new InvalidEmailException();
            }
        }

        if(senha == null || senha.isBlank()){
            throw new InvalidPasswordException();
        }

        if(endereco == null || endereco.isBlank()){
            throw new InvalidAddressException();
        }
    }
}
