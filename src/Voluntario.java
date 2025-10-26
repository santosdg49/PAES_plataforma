import java.util.ArrayList;

public class Voluntario extends Usuario {
    private ArrayList<Ajuda> historico_horas;

    public Voluntario(int id, String nome, String endereco, int idade, String cpf) {
        super(id, nome, endereco, idade, cpf);
        this.historico_horas = new ArrayList<Ajuda>();
    }

    public void Doar_horas(int horas, String local, String data) {
        Ajuda ajuda = new Ajuda(horas, local, data);
        historico_horas.add(ajuda);
    }

    public void consultar_historico() {
        System.out.println("Voluntário: " + this.getNome());
        for(Ajuda ajuda:historico_horas) {
            System.out.printf("horas trabalhadas: %.2f\n"
                    + "Local: %s\n"
                    + "Data: %s\n", ajuda.getHoras_trabalhadas(), ajuda.getLocal_trabalho(), ajuda.getData_trabalho());
            System.out.println();
        }
    }

    public ArrayList<Ajuda> getHistorico_horas(){
        return this.historico_horas;
    }

    public String getTipo() {
        return "Voluntario";
    }
}
