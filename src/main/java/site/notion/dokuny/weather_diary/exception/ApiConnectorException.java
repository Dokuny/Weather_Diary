package site.notion.dokuny.weather_diary.exception;

public class ApiConnectorException extends RuntimeException{

	public ApiConnectorException() {
	}

	public ApiConnectorException(String message) {
		super(message);
	}

	public ApiConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiConnectorException(Throwable cause) {
		super(cause);
	}

	public ApiConnectorException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
