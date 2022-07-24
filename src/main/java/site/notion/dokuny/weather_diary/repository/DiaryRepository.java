package site.notion.dokuny.weather_diary.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import site.notion.dokuny.weather_diary.domain.Diary;

public interface DiaryRepository extends JpaRepository<Diary,Long> {

	List<Diary> findAllByDate(LocalDate date);

	List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

	Optional<Diary> findFirstByDate(LocalDate date);

	void deleteAllByDate(LocalDate date);
}
