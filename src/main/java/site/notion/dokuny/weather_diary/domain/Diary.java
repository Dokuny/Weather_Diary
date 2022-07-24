package site.notion.dokuny.weather_diary.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,length = 50)
	private String weather;

	@Column(nullable = false)
	private double temperature;

	@Column(nullable = false, length = 50)
	private String icon;

	@Column(nullable = false,length = 500)
	private String text;

	@Column(nullable = false)
	private LocalDate date;


}
