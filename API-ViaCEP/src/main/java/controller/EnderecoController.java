package controller;

import model.Endereco;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class EnderecoController {
    public Endereco buscaEndereco(String cep) throws IOException, InterruptedException {

        String url = "https://viacep.com.br/ws/"+ cep + "/json/";

        HttpClient client = HttpClient.newHttpClient(); // Realiza operações HTTP facilitando o envio de requisições e recebimento de respostas

        HttpRequest request = HttpRequest               // Esse objeto tem o intuito de dar detalhes da requisição que será enviada
                .newBuilder()                           // Cria instancia um novo construtor de HttpRequest
                .uri(URI.create(url))                   // Condigura a URI(Uniform Resource Identifier) -> identificador de recursos na internet
                .build();                               // Constrói o objeto

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());   // Envia a requisição e a variável de instância "response" recebe a resposta.

        return new Gson().fromJson(response.body(), Endereco.class);

    }
}
