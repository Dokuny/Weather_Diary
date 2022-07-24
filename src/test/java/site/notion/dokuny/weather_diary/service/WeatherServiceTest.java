package site.notion.dokuny.weather_diary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Optional;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import site.notion.dokuny.weather_diary.domain.Weather;
import site.notion.dokuny.weather_diary.exception.WeatherException;
import site.notion.dokuny.weather_diary.repository.WeatherRepository;
import site.notion.dokuny.weather_diary.service.impl.WeatherServiceImpl;
import site.notion.dokuny.weather_diary.utils.ApiConnector;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

	@Mock
	WeatherRepository weatherRepository;

	@InjectMocks
	WeatherServiceImpl weatherService;

	static MockedStatic<ApiConnector> connector;

	@BeforeEach
	public void beforeClass() {
		connector = mockStatic(ApiConnector.class);
	}

	@AfterEach
	public  void afterClass() {
		connector.close();
	}

	@Test
	void getWeather_success() {
		//given
		Weather weather = Weather.builder()
			.date(LocalDate.now())
			.weather("Clouds")
			.temperature(34.1)
			.icon("testIcon")
			.build();

		given(weatherRepository.findById(any()))
			.willReturn(Optional.of(weather));

		//when
		Weather find = weatherService.getWeather(LocalDate.now());

		//then
		assertThat(find.getWeather()).isEqualTo(weather.getWeather());
		assertThat(find.getIcon()).isEqualTo(weather.getIcon());
		assertThat(find.getTemperature()).isEqualTo(weather.getTemperature());
		assertThat(find.getDate()).isEqualTo(weather.getDate());
	}

	@Test
	void getWeather_fail() {
		//given
		given(weatherRepository.findById(any()))
			.willReturn(Optional.empty());

		//when
		//then
		assertThatThrownBy(() -> weatherService.getWeather(LocalDate.now()))
			.isInstanceOf(WeatherException.class)
			.hasMessage("해당하는 요일의 날씨가 없습니다.");
	}

	@Test
	void saveTodayWeather_success() {
		//given
		Weather weather = Weather.builder()
			.date(LocalDate.now())
			.weather("Clouds")
			.icon("04n")
			.temperature(295.77)
			.build();

		given(weatherRepository.save(any()))
			.willReturn(weather);

		given(ApiConnector.getResponseString(anyString()))
			.willReturn(
				"{\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"icon\":\"04n\"}],\"main\":{\"temp\":295.77}}");


		ArgumentCaptor<Weather> captor = ArgumentCaptor.forClass(Weather.class);

		//when
		weatherService.saveTodayWeather();

		//then
		verify(weatherRepository).save(captor.capture());

		assertThat(captor.getValue().getDate()).isEqualTo(weather.getDate());
		assertThat(captor.getValue().getWeather()).isEqualTo(weather.getWeather());
		assertThat(captor.getValue().getIcon()).isEqualTo(weather.getIcon());
		assertThat(captor.getValue().getTemperature()).isEqualTo(weather.getTemperature());

	}


}