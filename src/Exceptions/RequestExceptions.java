package YandexWeather.Exceptions;

public class RequestExceptions extends RuntimeException {
    public RequestExceptions(String message) {
        super(message);
    }
}
