package site.notion.dokuny.weather_diary.exception;

public class WeatherException extends RuntimeException{

	public WeatherException() {
	}

	public WeatherException(String message) {
		super(message);
	}

	public WeatherException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeatherException(Throwable cause) {
		super(cause);
	}

	public WeatherException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
