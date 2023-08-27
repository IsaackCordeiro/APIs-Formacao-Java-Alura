package application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.ErroDeConversaoDeAnoException;
import model.Titulo;
import model.TituloOmdb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        String op = "";
        String filme;
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


        do {
            System.out.println("Digite o filme a buscar: ");
            filme = sc.nextLine();

            String endereco = "https://www.omdbapi.com/?t=" + filme.toLowerCase().replace(" ", "+") + "&apikey=9e7dc4f1";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, BodyHandlers.ofString());

                String json = response.body();

                System.out.println(json);

//        Titulo titulo = gson.fromJson(json, Titulo.class);
//
//        System.out.println(titulo.getNome());

                TituloOmdb titulo2 = gson.fromJson(json, TituloOmdb.class);
                System.out.println(titulo2);

                Titulo meuTitulo = new Titulo(titulo2);
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

            } catch (NumberFormatException e) { // Tratando exceção de formato de número inválido
                System.out.println("Aconteceu um erro");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Algum erro de argumento inválido na busca, verifique o endereço");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
            System.out.println("Deseja continuar? Digite qualquer coisa para Sim e digite \"Sair\" para sair do programa");
            op = sc.nextLine();

        } while(!op.equalsIgnoreCase("sair"));

        System.out.println(titulos);

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
    }
}
