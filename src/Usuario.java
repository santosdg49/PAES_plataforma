
public abstract class Usuario {
    private int id;
    private String nome;
    private String endereco;
    private int idade;
    private String cpf;

    public Usuario(int id, String nome, String endereco, int idade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
        this.cpf = cpf;
    }


    public abstract void consultar_historico();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }




}
