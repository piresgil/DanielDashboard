package dashboard.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dashboard.model.Noticia;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class NewsService {

    private static final String URL = "https://github.com/piresgil/DanielDashboard/news.json";


    public JsonObject fetchJson() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), JsonObject.class);
    }

    public List<Noticia> getFutebol(JsonObject json) {
        return Arrays.asList(new Gson().fromJson(json.get("futebol"), Noticia[].class));
    }

    public List<Noticia> getHoquei(JsonObject json) {
        return Arrays.asList(new Gson().fromJson(json.get("hoquei"), Noticia[].class));
    }
}
