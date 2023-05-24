package YandexWeather.Server;

import YandexWeather.Exceptions.RequestExceptions;
import YandexWeather.RequestData.RequestData;
import YandexWeather.Service.Processing;
import YandexWeather.Service.Request;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WeatherHandler implements HttpHandler {
    protected static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final String apiKey;
    private final String lat;
    private final String lon;

    WeatherHandler(String apiKey, String lat, String lon) {
        this.apiKey = apiKey;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder response = new StringBuilder();

        if (exchange.getRequestMethod().equals("GET")) {
            try {
                RequestData data = new Request(apiKey, lat, lon).doRequest();
                if (data == null) {
                    throw new RequestExceptions("Пустой ответ от API Яндекса");
                }
                List<String> html = new Processing().doProcessing(data);
                for (String line : html) {
                    response.append(line);
                }
                writeResponse(exchange, response.toString(), 200);
            } catch (RequestExceptions | IOException e) {
                System.out.println("Ошибка запроса: " + e.getMessage());
            }
        } else {
            writeResponse(exchange, "Не поддерживается", 400);
        }
    }

    protected void writeResponse(HttpExchange exchange, String response, int rCode) throws IOException {
        if (response.isBlank()) {
            exchange.sendResponseHeaders(rCode, 0);
        } else {
            Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", "text/html; charset=utf-8");
            byte[] bytes = response.getBytes(DEFAULT_CHARSET);
            exchange.sendResponseHeaders(rCode, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
        exchange.close();
    }
}
