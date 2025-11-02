import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ClienteCLI {

    private static final String API_URL = "http://localhost:8080/api/itens";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== MENU =====");
            System.out.println("1) Listar itens");
            System.out.println("2) Criar item");
            System.out.println("3) Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    listarItens();
                    break;
                case "2":
                    criarItem(sc);
                    break;
                case "3":
                    continuar = false;
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    private static void listarItens() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta JSON: " + response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void criarItem(Scanner sc) {
        try {
            System.out.print("Digite o nome do item: ");
            String nome = sc.nextLine();
            System.out.print("Digite a quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine());

            // Montando JSON manualmente
            String json = String.format("{\"nome\":\"%s\",\"quantidade\":%d}", nome, qtd);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Resposta JSON: " + response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}