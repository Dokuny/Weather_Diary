package site.notion.dokuny.weather_diary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@SpringBootApplication
public class WeatherDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDiaryApplication.class, args);
	}

}
