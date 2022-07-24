package site.notion.dokuny.weather_diary.service;

import java.time.LocalDate;
import site.notion.dokuny.weather_diary.domain.Weather;

public interface WeatherService {

	Weather getWeather(LocalDate date);

	void saveTodayWeather();

}
