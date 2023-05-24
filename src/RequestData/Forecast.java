package YandexWeather.RequestData;

public class Forecast {
    public String date;
    public long date_ts;
    public int week;
    public String sunrise;
    public String sunset;
    public int moon_code;
    public String moon_text;
    public ForecastParts[] parts = new ForecastParts[2];
}
