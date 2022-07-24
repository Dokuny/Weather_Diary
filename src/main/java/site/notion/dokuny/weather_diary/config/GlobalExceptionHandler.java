package site.notion.dokuny.weather_diary.config;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.notion.dokuny.weather_diary.dto.ResponseResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity handleException(RuntimeException e) {
		return ResponseEntity.internalServerError().body(ResponseResult.builder()
			.status("fail")
			.content(e.getMessage()).build());
	}

}
