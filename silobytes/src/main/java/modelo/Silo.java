package modelo;

/**
 * Silo com controle de capacidade (em toneladas).
 * Alimenta a barra "Status do Silo" (ex.: 450t / 1000t ocupadas).
 */
public class Silo {

    private String nome;              // identificador (ex.: "Silo 1")
    private double capacidadeMaxima;  // em toneladas
    private double quantidadeAtual;   // em toneladas, ocupação atual

    public Silo() {
        this.nome = "";
        this.capacidadeMaxima = 0;
        this.quantidadeAtual = 0;
    }

    public Silo(String nome, double capacidadeMaxima) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.quantidadeAtual = 0;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(double capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public double getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(double quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }

    // Os métodos abaixo NÃO começam com "get" de propósito: assim não viram
    // atributos gravados no MongoDB (são calculados a partir dos outros dois).
    public double espacoDisponivel() {
        return capacidadeMaxima - quantidadeAtual;
    }

    public double percentualOcupado() {
        return capacidadeMaxima == 0 ? 0 : quantidadeAtual / capacidadeMaxima;
    }

    @Override
    public String toString() {
        return nome + " (" + quantidadeAtual + "t / " + capacidadeMaxima + "t)";
    }
}