package dashboard.controller;

import com.google.gson.JsonObject;
import dashboard.model.Noticia;
import dashboard.service.NewsService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class MainController {

    @FXML
    private ListView<String> listaNoticias;

    private final NewsService service = new NewsService();
    private JsonObject json;

    @FXML
    public void initialize() {
        atualizar();
    }

    public void atualizar() {
        try {
            json = service.fetchJson();
            System.out.println("JSON carregado: " + json);
            mostrarFutebol();
        } catch (Exception e) {
            e.printStackTrace();
            listaNoticias.getItems().setAll("Erro ao carregar notícias.");
        }
    }


    public void mostrarFutebol() {
        mostrar(service.getFutebol(json));
    }

    public void mostrarHoquei() {
        mostrar(service.getHoquei(json));
    }

    private void mostrar(List<Noticia> noticias) {
        listaNoticias.getItems().clear();
        for (Noticia n : noticias) {
            listaNoticias.getItems().add(n.getTitulo() + " — " + n.getFonte());
        }
    }
}
