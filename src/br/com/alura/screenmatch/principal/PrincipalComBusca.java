package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 *
 * @author viniberaldo
 */
public class PrincipalComBusca {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digita um filme para busca: ");
        String busca = leitura.nextLine();
        String uri = "http://omdbapi.com/?t=" + busca + "&apikey=5bc598db";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(uri)).build();
        HttpResponse<String> response = client.
                send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
