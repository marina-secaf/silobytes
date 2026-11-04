package modelo;

import java.util.Date;
import java.util.UUID;

/**
 * Registro de armazenagem: a soja de um produtor guardada em um silo.
 * A entrada preenche produtor, silo, quantidade e dataEntrada.
 * A saída preenche dataSaida, valorTotal e pago.
 */
public class Armazenagem {

    private String codigo;      // identificador (gerado automaticamente)
    private Produtor produtor;
    private String silo;        // nome do silo (referência ao Silo)
    private double quantidadeToneladas;
    private Date dataEntrada;
    private Date dataSaida;     // null enquanto a soja ainda está no silo
    private double valorTotal;  // toneladas x dias x tarifa (calculado na saída)
    private boolean pago;       // false = "Em Aberto", true = "Pago"

    public Armazenagem() {
        this.codigo = "";
        this.silo = "";
    }

    public Armazenagem(Produtor produtor, String silo,
                       double quantidadeToneladas, Date dataEntrada) {
        this.codigo = UUID.randomUUID().toString();
        this.produtor = produtor;
        this.silo = silo;
        this.quantidadeToneladas = quantidadeToneladas;
        this.dataEntrada = dataEntrada;
        this.pago = false;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Produtor getProdutor() { return produtor; }
    public void setProdutor(Produtor produtor) { this.produtor = produtor; }

    public String getSilo() { return silo; }
    public void setSilo(String silo) { this.silo = silo; }

    public double getQuantidadeToneladas() { return quantidadeToneladas; }
    public void setQuantidadeToneladas(double quantidadeToneladas) { this.quantidadeToneladas = quantidadeToneladas; }

    public Date getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(Date dataEntrada) { this.dataEntrada = dataEntrada; }

    public Date getDataSaida() { return dataSaida; }
    public void setDataSaida(Date dataSaida) { this.dataSaida = dataSaida; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public boolean isPago() { return pago; }
    public void setPago(boolean pago) { this.pago = pago; }

    @Override
    public String toString() {
        return produtor + " - " + quantidadeToneladas + "t";
    }
}