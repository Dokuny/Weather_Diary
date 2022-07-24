package site.notion.dokuny.weather_diary.service.impl;


import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.notion.dokuny.weather_diary.domain.Weather;
import site.notion.dokuny.weather_diary.exception.WeatherException;
import site.notion.dokuny.weather_diary.repository.WeatherRepository;
import site.notion.dokuny.weather_diary.service.WeatherService;
import site.notion.dokuny.weather_diary.utils.ApiConnector;
import site.notion.dokuny.weather_diary.utils.JsonParser;


@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${open-weather-map.key}")
	private String apiKey;

	@Value("${open-weather-map.url.today}")
	private String todayUrl;

	private final WeatherRepository weatherRepository;

	@Transactional(readOnly = true)
	@Override
	public Weather getWeather(LocalDate date) {
		return weatherRepository.findById(date)
			.orElseThrow((()-> new WeatherException("해당하는 요일의 날씨가 없습니다.")));
	}

	@Transactional
	@Scheduled(cron = "0 0 1 * * *")
	@Override
	public void saveTodayWeather() {
		log.info("Spring Scheduler - Today Weather Saving....");
		weatherRepository.save(getWeatherFromApi(todayUrl + apiKey));
		log.info("Completed saving Weather when {} ", LocalDate.now());

	}

	private Weather getWeatherFromApi(String apiUrl) {
		JSONObject jsonObject = JsonParser.parse(ApiConnector.getResponseString(apiUrl));
		JSONObject mainData = (JSONObject) jsonObject.get("main");
		JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
		JSONObject weatherData = (JSONObject) weatherArray.get(0);

		return Weather.builder()
			.weather((String) weatherData.get("main"))
			.icon((String) weatherData.get("icon"))
			.temperature((Double) mainData.get("temp"))
			.date(LocalDate.now())
			.build();

	}


}
