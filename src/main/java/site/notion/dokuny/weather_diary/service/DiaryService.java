package site.notion.dokuny.weather_diary.service;

import java.time.LocalDate;
import java.util.List;
import site.notion.dokuny.weather_diary.domain.Diary;

public interface DiaryService {

	void createDiary(LocalDate date, String text);

	List<Diary> readDiary(LocalDate date);

	List<Diary> readDiaries(LocalDate startDate, LocalDate endDate);

	void updateDiary(LocalDate date, String text);

	void deleteDiary(LocalDate date);
}
