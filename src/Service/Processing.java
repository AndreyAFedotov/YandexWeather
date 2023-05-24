package YandexWeather.Service;

import YandexWeather.RequestData.RequestData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Processing {
    private final List<String> html;

    public  Processing() {
       this.html =  new ArrayList<>();
    }

    public List<String> doProcessing(RequestData data) {
        html.clear();
        html.add("<!DOCTYPE html>");
        html.add("<html lang=\"ru\">");
        html.add("<head>");
        html.add("<meta charset=\"UTF-8\">");
        html.add("<title>YandexWeather</title>");
        html.add("</head>");
        html.add("<body>");
        html.add("<table style=\"height: 563px; width: 652px; border-collapse: collapse;\" border=\"0\">");
        html.add("<tbody>");
        html.add("<tr style=\"height: 59px;\">");
        html.add("<td style=\"width: 603.82px; height: 59px;\">");
        html.add("<table style=\"border-collapse: collapse; width: 100%; height: 53px;\" border=\"0\">");
        html.add("<tbody>");
        html.add("<tr style=\"height: 22px;\">");
        html.add("<td style=\"width: 100%; height: 22px;\"><span style=\"color: #333399;\">");
        html.add("<strong>Прогноз получен: " + getDateTimeFromUnix(String.valueOf(data.now)) +
                ". <br /> Широта: " + data.info.lat + ". Долгота: " + data.info.lon + ".</strong></span></td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 31px;\">");
        html.add("<td style=\"width: 100%; height: 31px; text-align: center;\"><hr />" +
                "<span style=\"color: #993300;\"><span style=\"color: #993300;\">" +
                "<strong>Фактическая погода</strong></span></span><hr /></td>");
        html.add("</tr>");
        html.add("</tbody>");
        html.add("</table>");
        html.add("</td>");
        html.add("<td style=\"width: 41.1797px; height: 59px;\">&nbsp;</td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 108px;\">");
        html.add("<td style=\"width: 603.82px; height: 108px;\">");
        html.add("<table style=\"height: 104px; width: 100.229%; " +
                "border-collapse: collapse; border-style: none;\" border=\"0\">");
        html.add("<tbody>");
        html.add("<tr style=\"height: 86px;\">");
        html.add("<td style=\"width: 44.6666%; height: 104px;\">");
        html.add("<div>");
        html.add("Время суток: " + getDayTime(data.fact.daytime) + "<br />");
        html.add("Осадки: " + getCondition(data.fact.condition) + "<br />");
        html.add("Температура: " + data.fact.temp + " С&deg; <br />");
        html.add("Ощущается как: " + data.fact.feels_like + " С&deg; <br />");
        html.add("Влажность: " + data.fact.humidity + " %</div>");
        html.add("</td>");
        html.add("<td style=\"width: 55.9943%; height: 104px;\">");
        html.add("<div>");
        html.add("Скорость ветра: " + data.fact.wind_speed + " м.с.<br />");
        html.add("Порывы: " + data.fact.wind_gust + " м.с.<br />");
        html.add("Направление ветра: " + getWindDir(data.fact.wind_dir) + "<br />");
        html.add("Давление: " + data.fact.pressure_mm + " mmHg</div>");
        html.add("</td>");
        html.add("</tr>");
        html.add("</tbody>");
        html.add("</table>");
        html.add("</td>");
        html.add("<td style=\"width: 41.1797px; height: 108px; text-align: center;\">");
        html.add("<img src=\"https://yastatic.net/weather/i/icons/funky/dark/" + data.fact.icon +
                ".svg\" alt=\"\" /></td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 34px;\">");
        html.add("<td style=\"width: 603.82px; height: 34px; text-align: center;\"><hr />" +
                "<span style=\"color: #993300;\"><span style=\"color: #993300;\">");
        html.add("<strong>Прогноз на " + getPartName(data.forecast.parts[0].part_name) +
                "</strong></span></span><hr /></td>");
        html.add("<td style=\"width: 41.1797px; height: 34px;\">&nbsp;</td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 164px;\">");
        html.add("<td style=\"width: 603.82px; height: 164px;\">");
        html.add("<table style=\"height: 160px; width: 100.554%; border-collapse: collapse; " +
                "border-style: none;\" border=\"0\">");
        html.add("<tbody>");
        html.add("<tr style=\"height: 86px;\">");
        html.add("<td style=\"width: 44.5058%; height: 160px;\">");
        html.add("<div>");
        html.add("Время суток: " + getDayTime(data.forecast.parts[0].daytime) + "<br />");
        html.add("Осадки: " + getCondition(data.forecast.parts[0].condition) + "<br />");
        //html.add("Температура: 77 С&deg; <br />");
        html.add("Температура min: " + data.forecast.parts[0].temp_min + " С&deg; <br />");
        html.add("Температура max: " + data.forecast.parts[0].temp_max + " С&deg; <br />");
        html.add("Температура сред.: " + data.forecast.parts[0].temp_avg + " С&deg; <br />");
        html.add("Ощущается как: " + data.forecast.parts[0].feels_like + " С&deg; <br />");
        html.add("Влажность: " + data.forecast.parts[0].humidity + " %</div>");
        html.add("</td>");
        html.add("<td style=\"width: 56.3415%; height: 160px;\">");
        html.add("<div>");
        html.add("Скорость ветра: " + data.forecast.parts[0].wind_speed + " м.с.<br />");
        html.add("Порывы: " + data.forecast.parts[0].wind_gust + " м.с.<br />");
        html.add("Направление ветра: " + getWindDir(data.forecast.parts[0].wind_dir) + "<br />");
        html.add("Давление: " + data.forecast.parts[0].pressure_mm + " mmHg<br />");
        html.add("Прогнозируемое кол-во осадков: " + data.forecast.parts[0].prec_mm + " мм<br />");
        html.add("Прогнозируемый период осадков: " + data.forecast.parts[0].prec_period + " мин.<br />");
        html.add("Вероятность выпадения осадков: " + data.forecast.parts[0].prec_prob + " %</div>");
        html.add("</td>");
        html.add("</tr>");
        html.add("</tbody>");
        html.add("</table>");
        html.add("</td>");
        html.add("<td style=\"width: 41.1797px; height: 164px; text-align: center;\">" +
                "<img src=\"https://yastatic.net/weather/i/icons/funky/dark/" +
                data.forecast.parts[0].icon + ".svg\" alt=\"\" /></td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 34px;\">");
        html.add("<td style=\"width: 603.82px; height: 34px; text-align: center;\">" +
                "<hr /><span style=\"color: #993300;\"><span style=\"color: #993300;\">");
        html.add("<strong>Прогноз на " + getPartName(data.forecast.parts[1].part_name) +
                "</strong></span></span><hr /></td>");
        html.add("<td style=\"width: 41.1797px; height: 34px;\">&nbsp;</td>");
        html.add("</tr>");
        html.add("<tr style=\"height: 164px;\">");
        html.add("<td style=\"width: 603.82px; height: 164px;\">");
        html.add("<table style=\"height: 160px; width: 100.053%; border-collapse: collapse; " +
                "border-style: none;\" border=\"0\">");
        html.add("<tbody>");
        html.add("<tr style=\"height: 86px;\">");
        html.add("<td style=\"width: 44.6719%; height: 160px;\">");
        html.add("<div>");
        html.add("Время суток: " + getDayTime(data.forecast.parts[1].daytime) + "<br />");
        html.add("Осадки: " + getCondition(data.forecast.parts[1].condition) + "<br />");
        //html.add("Температура: 77 С&deg; <br />");
        html.add("Температура min: " + data.forecast.parts[1].temp_min + " С&deg; <br />");
        html.add("Температура max: " + data.forecast.parts[1].temp_max + " С&deg; <br />");
        html.add("Температура сред.: " + data.forecast.parts[1].temp_avg + " С&deg; <br />");
        html.add("Ощущается как: " + data.forecast.parts[1].feels_like + " С&deg; <br />");
        html.add("Влажность: " + data.forecast.parts[1].humidity + " %</div>");
        html.add("</td>");
        html.add("<td style=\"width: 55.8155%; height: 160px;\">");
        html.add("<div>");
        html.add("Скорость ветра: " + data.forecast.parts[1].wind_speed + " м.с.<br />");
        html.add("Порывы: " + data.forecast.parts[1].wind_gust + " м.с.<br />");
        html.add("Направление ветра: " + getWindDir(data.forecast.parts[1].wind_dir) + "<br />");
        html.add("Давление: " + data.forecast.parts[1].pressure_mm + " mmHg<br />");
        html.add("Прогнозируемое кол-во осадков: " + data.forecast.parts[1].prec_mm + " мм<br />");
        html.add("Прогнозируемый период осадков: " + data.forecast.parts[1].prec_period + " мин.<br />");
        html.add("Вероятность выпадения осадков: " + data.forecast.parts[1].prec_prob + " %</div>");
        html.add("</td>");
        html.add("</tr>");
        html.add("</tbody>");
        html.add("</table>");
        html.add("</td>");
        html.add("<td style=\"width: 41.1797px; height: 164px; text-align: center;\">" +
                "<img src=\"https://yastatic.net/weather/i/icons/funky/dark/" +
                data.forecast.parts[1].icon + ".svg\" alt=\"\" /></td>");
        html.add("</tr>");
        html.add("</tbody>");
        html.add("</table>");
        html.add("</body>");
        html.add("</html>");
        return html;
    }

    private String getPartName(String partName) {
        if (partName.equals("night")) return "ночь";
        if (partName.equals("morning")) return "утро";
        if (partName.equals("day")) return "день";
        if (partName.equals("evening")) return "вечер";
        return partName;
    }

    private String getDayTime(String daytime) {
        if (daytime.equals("d")) return "Светлое";
        if (daytime.equals("n")) return "Тёмное";
        return daytime;
    }

    private String getWindDir(String windDir) {
        if (windDir.equals("nw")) return "Северо-запад";
        if (windDir.equals("n")) return "Север";
        if (windDir.equals("ne")) return "Северо-восток";
        if (windDir.equals("e")) return "Восток";
        if (windDir.equals("se")) return "Юго-восток";
        if (windDir.equals("s")) return "Юг";
        if (windDir.equals("sw")) return "Юго-запад";
        if (windDir.equals("w")) return "Запад";
        if (windDir.equals("c")) return "Штиль";
        return windDir;
    }

    private String getDateTimeFromUnix(String unixTime) {
        long time = Long.parseLong(unixTime);
        Instant inst = Instant.ofEpochSecond(time);
        ZoneId tz = ZoneId.of("Europe/Moscow");
        LocalDateTime dateTime = inst.atZone(tz).toLocalDateTime();
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy в HH:mm:ss");
        return dateTime.format(dtFormat);
    }

    private String getCondition(String condition) {
        if (condition.equals("clear")) return "Ясно";
        if (condition.equals("partly-cloudy")) return "Переменная облачность";
        if (condition.equals("cloudy")) return "Пасмурно";
        if (condition.equals("overcast")) return "Пасмурно";
        if (condition.equals("drizzle")) return "Моросящий дождь";
        if (condition.equals("light-rain")) return "Лёгкий дождь";
        if (condition.equals("rain")) return "Дождь";
        if (condition.equals("moderate-rain")) return "Умеренный дождь";
        if (condition.equals("heavy-rain")) return "Проливной дождь";
        if (condition.equals("continuous-heavy-rain")) return "Сильный проливной дождь";
        if (condition.equals("showers")) return "Ливень";
        if (condition.equals("wet-snow")) return "Мокрый снег";
        if (condition.equals("light-snow")) return "Лёгкий снег";
        if (condition.equals("snow")) return "Снег";
        if (condition.equals("snow-showers")) return "Снегопад";
        if (condition.equals("hail")) return "Град";
        if (condition.equals("thunderstorm")) return "Гроза";
        if (condition.equals("thunderstorm-with-rain")) return "Гроза с дождём";
        if (condition.equals("thunderstorm-with-hail")) return "Гроза с градом";
        return condition;
    }

}
