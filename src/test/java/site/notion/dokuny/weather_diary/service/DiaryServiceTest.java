package site.notion.dokuny.weather_diary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.notion.dokuny.weather_diary.domain.Diary;
import site.notion.dokuny.weather_diary.domain.Weather;
import site.notion.dokuny.weather_diary.repository.DiaryRepository;
import site.notion.dokuny.weather_diary.service.impl.DiaryServiceImpl;
import site.notion.dokuny.weather_diary.service.impl.WeatherServiceImpl;

@ExtendWith(MockitoExtension.class)
class DiaryServiceTest {

	@Mock
	DiaryRepository diaryRepository;

	@Mock
	WeatherServiceImpl weatherService;

	@InjectMocks
	DiaryServiceImpl diaryService;


	@Test
	void createDiary_Success() {
		//given
		LocalDate date = LocalDate.now();
		String text = "test";

		Weather weather = Weather.builder()
			.temperature(34.1)
			.icon("test")
			.weather("cloud")
			.date(date)
			.build();

		Diary diary = Diary.builder()
			.temperature(weather.getTemperature())
			.icon(weather.getIcon())
			.weather(weather.getWeather())
			.date(date)
			.text(text)
			.build();

		given(weatherService.getWeather(date)).willReturn(weather);
		given(diaryRepository.save(any()))
			.willReturn(diary);

		ArgumentCaptor<Diary> captor = ArgumentCaptor.forClass(Diary.class);

		//when
		diaryService.createDiary(date,text);

		//then
		verify(diaryRepository).save(captor.capture());
		assertThat(captor.getValue().getIcon()).isEqualTo(weather.getIcon());
		assertThat(captor.getValue().getTemperature()).isEqualTo(weather.getTemperature());
		assertThat(captor.getValue().getWeather()).isEqualTo(weather.getWeather());
		assertThat(captor.getValue().getDate()).isEqualTo(date);
		assertThat(captor.getValue().getText()).isEqualTo(text);
	}
}