package YandexWeather;

import YandexWeather.Server.HTTPWeatherServer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String API_KEY = "<TOKEN>";
    private static final String LAT = "55.7522";
    private static final String LON = "37.615";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        boolean work = true;

        new HTTPWeatherServer(PORT, API_KEY, LAT, LON);
        while (work) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter \"exit\" to quit: ");
            String cmd = scanner.nextLine();
            if (cmd.equalsIgnoreCase("exit")) {
                work = false;
            } else {
                System.out.println("Wrong command!");
            }
        }
        System.exit(0);
    }
}
