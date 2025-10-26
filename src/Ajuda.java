public class Ajuda extends Evento{

    private int Horas_trabalhadas;

    public Ajuda(int horas_trabalhadas, String local_trabalho, String data_trabalho) {
        super(local_trabalho, data_trabalho);
        Horas_trabalhadas = horas_trabalhadas;
    }

    public int getHoras_trabalhadas() {
        return Horas_trabalhadas;
    }

}
