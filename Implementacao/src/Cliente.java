import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String cnpj;
    private ArrayList<Usuario> Contribuidores;
    private ArrayList<Evento> eventos;

    public Cliente(int id, String nome, String endereco, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.Contribuidores = new ArrayList<Usuario>();
    }

    public void criar_doacao(String local_doacao, String data_doacao, double valor) {
        Doacao doacao = new Doacao(valor, local_doacao, data_doacao);
        eventos.add(doacao);
    }

    public void criar_ajuda(String local_ajuda, String data_ajuda, int horas_trabalhadas) {
        Ajuda ajuda = new Ajuda(horas_trabalhadas, local_ajuda, data_ajuda);
        eventos.add(ajuda);
    }

    public void adicionar_contribuidor(Usuario contribuidor) {
        Contribuidores.add(contribuidor);
    }

    public void consultar_historico_usuario(Usuario contribuidor ) {
        contribuidor.consultar_historico();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public ArrayList<Usuario> getContribuidores() {
        return Contribuidores;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }


}

