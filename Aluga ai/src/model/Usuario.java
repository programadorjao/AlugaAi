package model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private static int contador = 1;

    public Usuario(String nome, String email, String senha, String telefone){
        this.id = contador++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public void verificarCadastro(){
        if(nome == null || email == null){
            System.out.println("Dados inválidos");
            return;
        }
        System.out.println("Usuário cadastrado com sucesso");
    }

    public boolean login(String emailDigitado, String senhaDigitada){
        return this.email.equals(emailDigitado)
                && this.senha.equals(senhaDigitada);
    }

    public void atualizarDados(String novoNome, String novoTelefone){
        this.nome = novoNome;
        this.telefone = novoTelefone;
    }

    public void alterarSenha(String atual, String nova){
        if(this.senha.equals(atual)){
            this.senha = nova;
        }
    }

    //getters
    public String getNome() {return nome;}
    public String getEmail(){return email;}
    public String getTelefone() {return telefone;}
    public int getId() {return id;}

    //setters
    public void setNome(String nome) {this.nome = nome;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
}
