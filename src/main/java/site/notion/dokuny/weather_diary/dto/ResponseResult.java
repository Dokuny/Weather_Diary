package site.notion.dokuny.weather_diary.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseResult<T>{

	public static final String FAIL = "fail";
	public static final String SUCCESS = "success";


	private String status;
	private T content;


}
