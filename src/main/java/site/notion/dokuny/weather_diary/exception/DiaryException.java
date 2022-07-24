package site.notion.dokuny.weather_diary.exception;

public class DiaryException extends RuntimeException{

	public DiaryException() {
	}

	public DiaryException(String message) {
		super(message);
	}

	public DiaryException(String message, Throwable cause) {
		super(message, cause);
	}

	public DiaryException(Throwable cause) {
		super(cause);
	}

	public DiaryException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
