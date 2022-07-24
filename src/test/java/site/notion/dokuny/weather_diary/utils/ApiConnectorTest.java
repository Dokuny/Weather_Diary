package site.notion.dokuny.weather_diary.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.notion.dokuny.weather_diary.exception.ApiConnectorException;

class ApiConnectorTest {

	@Test
	@DisplayName("API 요청 후 응답을 String으로 변환 - 성공")
	void getResponseString_success(){
	    //given
		String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";

	    //when
		String responseString = ApiConnector.getResponseString(apiUrl);
		//then
		assertThat(responseString).isEqualTo("{  \"userId\": 1,  \"id\": 1,  \"title\": \"delectus aut autem\",  \"completed\": false}");
	}

	@Test
	@DisplayName("API 요청 후 응답을 String으로 변환 - 실패")
	void getResponseString_fail(){
	    //given
		String apiUrl = "";

	    //when
		//then
		assertThatThrownBy(()->ApiConnector.getResponseString(apiUrl))
			.isInstanceOf(ApiConnectorException.class)
			.hasMessage("failed to get response");
	}

}