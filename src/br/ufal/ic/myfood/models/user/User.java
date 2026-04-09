package br.ufal.ic.myfood.models.user;

public class User {
    String nome;
    String email;
    String senha;
    String endereco;
    String cpf;

    public User(String nome, String email, String senha, String endereco, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }
}
