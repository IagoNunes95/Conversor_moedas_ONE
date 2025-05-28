import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConversorMoedaService {
	
	private final HttpClient httpClient;
    private final String apiKey = "1147780d3ccfa0491b2c8861";
    private final String baseUrl = "https://v6.exchangerate-api.com/v6/";
    private final Gson gson;
	
    //método padrão para inicializar o httpclient e o gson na instanciação da classe.
    public ConversorMoedaService() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }
    
    
    //método para acessar a API da exchangerate e retornar o resultado da conversão.
    public double converter(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        String url = baseUrl + apiKey + "/pair/" + moedaOrigem + "/" + moedaDestino + "/" + valor;
        
        //criando a "carta" para requisição.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        //realizando a requisição
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        //Transformando o retorno(Json) em um objeto java do tipo json.
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        
        
        //realizando o tratamento do resultado caso dê erro.
        if (json.get("result").getAsString().equals("success")) {
            return json.get("conversion_result").getAsDouble();
        } else {
            throw new RuntimeException("Erro na conversão: " + json.get("error-type").getAsString());
        }
    }	
		
        
}
