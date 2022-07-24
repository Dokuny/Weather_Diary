package site.notion.dokuny.weather_diary.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import site.notion.dokuny.weather_diary.domain.Weather;

public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {

}
