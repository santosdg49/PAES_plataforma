public class Evento {
    private String Local_evento;
    private String Data_data_evento;

    public Evento(String Local_evento, String Data_data_evento) {
        this.Local_evento = Local_evento;
        this.Data_data_evento = Data_data_evento;
    }

    public String getLocal_evento() {
        return Local_evento;
    }

    public String getData_data_evento() {
        return Data_data_evento;
    }
}
