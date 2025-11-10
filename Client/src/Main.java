import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final String CLientURl ="http://localhost:8080/api/plataforma/clientes";
    private static final String EventoURl ="http://localhost:8081/api/plataforma/eventos";
    private static final String HistoricoURl ="http://localhost:8082/api/plataforma/historicos";
    private static final String UsuarioURl ="http://localhost:8083/api/plataforma/usuarios";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        criarUsuario(sc);

    }

    private static void criarUsuario(Scanner sc) {
        try {
            System.out.print("Isira seu nome: ");
            String nome = sc.nextLine();
            System.out.print("Insira seu endereço: ");
            String end = sc.nextLine();
            System.out.print("Insira sua idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            System.out.print("Insira seu CPF: ");
            String cpf = sc.nextLine();

            // Montando JSON manualmente
            String json = String.format("{\"nome\":\"%s\",\"endereco\":\"%s\",\"idade\":\"%d\",\"cpf\":%s}",
                    nome, end, idade, cpf);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(UsuarioURl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta JSON: " + response.body());

            JSONObject jsonObject = new JSONObject(response.body());
            UUID userID = UUID.fromString(jsonObject.getString("ID"));
            criarHistorico(userID);



        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void  criarHistorico(UUID UsuarioID) {
        try {
            String json = String.format("{\"UsuarioID\":\"%s\"}", UsuarioID);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest requestHist = HttpRequest.newBuilder()
                    .uri(URI.create(HistoricoURl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(requestHist, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta JSON: " + response.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}