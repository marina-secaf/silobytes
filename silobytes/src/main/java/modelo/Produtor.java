package modelo;

/**
 * Produtor rural, cliente que aluga espaço no silo.
 * Campos baseados na tela de cadastro (nome, CPF/CNPJ, telefone/contato).
 */
public class Produtor {

    private String cpfCnpj;  // identificador
    private String nome;
    private String telefone;

    public Produtor() {
        this.cpfCnpj = "";
        this.nome = "";
        this.telefone = "";
    }

    public Produtor(String cpfCnpj, String nome, String telefone) {
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Aparece nos ComboBox de "Selecionar Produtor"
    @Override
    public String toString() {
        return nome + " - " + cpfCnpj;
    }
}