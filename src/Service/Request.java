package YandexWeather.Service;

import YandexWeather.Exceptions.RequestExceptions;
import YandexWeather.RequestData.RequestData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private static final HttpClient.Version CLIENT_VER = HttpClient.Version.HTTP_1_1;
    private final String apiKey;
    private final HttpClient client;
    private final URI uri;

    public Request(String apiKey, String lat, String lon) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        uri = URI.create("https://api.weather.yandex.ru/v2/informers?lat=" + lat + "&lon=" + lon + "&lang=ru_RU");
    }

    public RequestData doRequest() throws RequestExceptions {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .headers("Accept", "*/*")
                    .headers("X-Yandex-API-Key", apiKey)
                    .headers("Content-Type", "application/json")
                    .version(CLIENT_VER)
                    .GET()
                    .build();
            HttpResponse<String> response = send(request);
            return acceptData(response);
        } catch (RequestExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private HttpResponse<String> send(HttpRequest request) throws RequestExceptions {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RequestExceptions("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    private RequestData acceptData(HttpResponse<String> response) throws RequestExceptions {
        Gson gson = new GsonBuilder().create();
        try {
            return gson.fromJson(response.body(), RequestData.class);
        } catch (JsonSyntaxException e) {
            throw new RequestExceptions("Ошибка JSON: " + e.getMessage());
        }
    }
}
