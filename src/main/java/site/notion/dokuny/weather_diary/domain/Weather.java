package site.notion.dokuny.weather_diary.domain;


import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Weather {

	@Id
	private LocalDate date;

	@Column(nullable = false)
	private Double temperature;
	@Column(nullable = false)
	private String weather;
	@Column(nullable = false)
	private String icon;

}
