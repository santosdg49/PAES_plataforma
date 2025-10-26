
public class Doacao extends Evento{
    private Double Valor;

    public Doacao(Double valor, String local_doacao, String data_doacao) {
        super(local_doacao, data_doacao);
        Valor = valor;
    }

    public Double getValor() {
        return Valor;
    }

}

