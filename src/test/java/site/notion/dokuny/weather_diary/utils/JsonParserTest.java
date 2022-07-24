package site.notion.dokuny.weather_diary.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import site.notion.dokuny.weather_diary.exception.JsonParserException;

class JsonParserTest {


	@Test
	void parse_success(){
	    //given
		String json = "{\"id\" : 10, \"name\" : \"test\"}";

	    //when
		JSONObject object = JsonParser.parse(json);

		//then
		assertThat(object.get("id")).isEqualTo(10L);
		assertThat(object.get("name")).isEqualTo("test");
	}

	@Test
	void parse_fail(){
	    //given
		String json = "";

	    //when
		//then
		assertThatThrownBy(() -> JsonParser.parse(json))
			.isInstanceOf(JsonParserException.class)
			.hasMessage("failed to parse String to JSONObject");

	}
}