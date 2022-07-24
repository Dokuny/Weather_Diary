package site.notion.dokuny.weather_diary.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.notion.dokuny.weather_diary.domain.Diary;
import site.notion.dokuny.weather_diary.domain.Weather;
import site.notion.dokuny.weather_diary.exception.DiaryException;
import site.notion.dokuny.weather_diary.repository.DiaryRepository;
import site.notion.dokuny.weather_diary.service.DiaryService;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

	private final DiaryRepository diaryRepository;

	private final WeatherServiceImpl weatherService;

	@Transactional
	@Override
	public void createDiary(LocalDate date,String text) {
		log.info("started to create diary");
		Weather weather = weatherService.getWeather(date);

		diaryRepository.save(Diary.builder()
			.temperature(weather.getTemperature())
			.icon(weather.getIcon())
			.weather(weather.getWeather())
			.date(weather.getDate())
			.text(text)
			.build());
		log.info("end to create diary");
	}

	@Transactional(readOnly = true)
	@Override
	public List<Diary> readDiary(LocalDate date) {
		return diaryRepository.findAllByDate(date);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
		return diaryRepository.findAllByDateBetween(startDate, endDate);
	}

	@Transactional
	@Override
	public void updateDiary(LocalDate date, String text) {
		Diary diary = diaryRepository.findFirstByDate(date)
			.orElseThrow(() -> new DiaryException("해당 날짜에 일기가 존재하지 않습니다."));

		diary.setText(text);
	}
	@Transactional
	public void deleteDiary(LocalDate date) {
		diaryRepository.deleteAllByDate(date);
	}


}
