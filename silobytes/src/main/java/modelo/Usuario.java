package modelo;

/**
 * Operador do sistema (funcionário da empresa de armazenamento).
 * Usado na tela de login.
 */
public class Usuario {

    private String login;  // identificador (campo "Usuário" da tela de login)
    private String nome;   // nome completo do operador
    private String senha;

    public Usuario() {
        this.login = "";
        this.nome = "";
        this.senha = "";
    }

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return nome + " (" + login + ")";
    }
}