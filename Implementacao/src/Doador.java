import java.util.ArrayList;

public class Doador extends Usuario {
    private int Numero_conta;
    private ArrayList<Doacao> historico_doacoes;

    public Doador(int id, String nome, String endereco, int idade, String cpf, int Numero_conta) {
        super(id, nome, endereco, idade, cpf);
        this.Numero_conta = Numero_conta;
        this.historico_doacoes = new ArrayList<Doacao>();
    }

    public void Doar(Double valor, String local, String data) {
        Doacao doacao = new Doacao(valor, local, data);
        historico_doacoes.add(doacao);
    }

    public void consultar_historico() {
        System.out.println("Doador: " + this.getNome());
        for(Doacao doacao:historico_doacoes) {
            System.out.printf("Valor: %.2f\nLocal: %s\nData: %s\n", doacao.getValor(), doacao.getLocal_evento(), doacao.getData_data_evento());
            System.out.println();
        }
    }

    public int getNumero_conta() {
        return Numero_conta;
    }

    public ArrayList<Doacao> getHistorico_doacoes() {
        return historico_doacoes;
    }

    public String getTipo() {
        return "Doador";
    }



}
