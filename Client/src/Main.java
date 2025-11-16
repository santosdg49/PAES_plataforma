import org.json.JSONObject;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
        //criarUsuario(sc);
        //System.out.println();
        //criarEvento(sc);
        //System.out.println();
        //ListarEventos();
        //System.out.println();
        //AdicionarEventoNoHistorico(sc);

        //criarUsuario(sc);

        UUID u = UUID.fromString("b05f34cd-a95a-4766-ae8d-cb2546b67993");

        System.out.println(obterHistoricoPorUsuario(u));

        //criarEvento(sc);

        UUID e = UUID.fromString("fad9ffb0-0af0-4ac6-8f84-7e4dc17cd525");

        AdicionarEventoNoHistorico(sc);


    }

    // CRIA USUARIO E SEU HISTORICO
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
            String json = String.format("{\"nome\":\"%s\",\"endereco\":\"%s\",\"idade\":%d,\"cpf\":%s}",
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


    // CRIA O HISTORICO - FUNÇÃO USADA NA CRIAÇÃO DO USUARIO
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

    // CRIA O CLIENTE
    private static void criarCliente(Scanner sc) {
        try {
            System.out.print("Isira o nome da instituição: ");
            String nome = sc.nextLine();
            System.out.print("Insira seu endereço: ");
            String end = sc.nextLine();
            System.out.print("Insira seu CNPJ: ");
            String cnpj = sc.nextLine();

            String json = String.format("{\"nome\":\"%s\",\"endereco\":\"%s\",\"cnpj\":\"%s\"}",
                    nome, end, cnpj);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CLientURl))
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

    // CRIA O EVENTO
    public static void criarEvento(Scanner sc){
        try {
            System.out.print("Isira a instituição responsável pelo evento: ");
            String inst = sc.nextLine();
            System.out.print("Insira o Local do evento: ");
            String local = sc.nextLine();
            System.out.print("Insira a data do evento: ");
            String data = sc.nextLine();
            System.out.print("Insira o valor a ser doado: ");
            Double valor = sc.nextDouble();
            sc.nextLine();

            String json = String.format("{\"instituicao_responsavel\":\"%s\",\"Local_evento\":\"%s\",\"" +
                            "Data_evento\":\"%s\",\"valor\":%s}",
                    inst, local, data, valor);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(EventoURl))
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

    // ADICIONA UM EVENTO EXISTENTE NO HISTORICO DE UM USUARIO
    public static void AdicionarEventoNoHistorico(Scanner sc){
        System.out.print("Insira o ID od usuario: ");
        UUID usuarioID = UUID.fromString(sc.nextLine());
        System.out.print("Insira o ID do evento: ");
        UUID eventoID = UUID.fromString(sc.nextLine());

        // 1. Buscar o histórico do usuário
        UUID historicoID = obterHistoricoPorUsuario(usuarioID);

        if (historicoID == null) {
            System.out.println("Histórico não encontrado para esse usuário.");
            return;
        }

        // 2. Buscar evento existente
        JSONObject evento = obterEvento(eventoID);

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        try {
            // Montar JSON no formato do NovoEventoDTO
            String json = String.format(
                    "{\"EventoID\":\"%s\",\"Instituicao_responsavel\":\"%s\", " +
                            "\"Local_evento\":\"%s\", " +
                            "\"Data_evento\":\"%s\", " +
                            "\"valor\":%s}",
                    evento.getString("ID"),
                    evento.getString("instituicao_responsavel"),
                    evento.getString("Local_evento"),
                    evento.getString("Data_evento"),
                    evento.getDouble("valor")
            );

            // 3. Chamar API de histórico para adicionar evento
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(HistoricoURl + "/" + historicoID + "/eventos"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + res.statusCode());
            System.out.println("Resposta: " + res.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // LISTA OS EVENTOS QUE ESTÃO EM ARMAZENAMENTO LOCAL
    public static void ListarEventos(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(EventoURl))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Lista dos eventos:");
                System.out.println(response.body());
            }
            else{
                System.out.println("Não foi possível visualizar os eventos.");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // PESQUISA NO REPOSITÓRIO UM HISTORICO QUE TENHO USUARIOID IGUAL AO PASSADO NO PARÂMETRO E RETORNA UUID DO HISTORICO
    public static UUID obterHistoricoPorUsuario(UUID usuarioID) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HistoricoURl))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro ao buscar historicos.");
                return null;
            }

            var arr = new org.json.JSONArray(response.body());

            for (int i = 0; i < arr.length(); i++) {
                var h = arr.getJSONObject(i);
                if (h.getString("UsuarioID").equals(usuarioID.toString())) {
                    return UUID.fromString(h.getString("ID"));
                }
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // RETORNA O JSON DE UM EVENTO ESPECIFICO
    public static JSONObject obterEvento(UUID eventoID) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(EventoURl + "/" + eventoID))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> res =
                    client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() != 200) {
                System.out.println("Erro ao buscar evento");
                return null;
            }

            return new JSONObject(res.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}