package YandexWeather.Server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPWeatherServer {
    private final int port;
    private final HttpServer server;


     public HTTPWeatherServer(int port, String apiKey, String lat, String lon) throws IOException {
        this.port = port;
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);
        server.createContext("/", new WeatherHandler(apiKey, lat, lon));
        start();
    }

    public void start() {
        server.start();
        System.out.println("HTTP server on port:  " + port);
    }
}
