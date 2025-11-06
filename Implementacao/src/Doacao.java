
public class Doacao extends Evento{
    private Double Valor;
    private String local_doacao;
    private String data;

    public Doacao(Double valor, String local_doacao, String data_doacao) {
        super(local_doacao, data_doacao);
        this.Valor = valor;
    }

    public Double getValor() {
        return Valor;
    }

}

