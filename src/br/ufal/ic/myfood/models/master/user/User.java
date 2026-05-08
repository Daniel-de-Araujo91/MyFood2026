package br.ufal.ic.myfood.models.master.user;

public class User {
    private String name;
    private String email;
    private String password;
    private String address;
    private String cpf;

    public User(String name, String email, String password, String address, String cpf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCpf() {
        return cpf;
    }
}
