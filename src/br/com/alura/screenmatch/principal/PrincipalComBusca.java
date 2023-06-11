package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author viniberaldo
 */
public class PrincipalComBusca {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(
                FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        
        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digita um filme para busca: ");
            busca = leitura.nextLine();
            
            if (busca.equalsIgnoreCase("sair")) {
                break;
            }
            
            try {
                String uri = "http://omdbapi.com/?t=" + busca + "&apikey=5bc598db";
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().
                        uri(URI.create(uri)).build();
                HttpResponse<String> response = client.
                        send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                
                //Titulo meuTitulo = gson.fromJson(json, Titulo.class);
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                
                Titulo meuTitulo = null;
                //try {
                meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo convertido");
                System.out.println(meuTitulo);
                
                titulos.add(meuTitulo);
            } catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println("Erro de argumento na busca, verifique o endereço");
            }
            
            System.out.println("O programa finalizou corretamento");
        }
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
               
    }
}
