package site.notion.dokuny.weather_diary.utils;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import site.notion.dokuny.weather_diary.exception.JsonParserException;

@Slf4j
public class JsonParser {

	public static JSONObject parse(String json) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;

		try {
			jsonObject = (JSONObject) jsonParser.parse(json);
			return jsonObject;
		} catch (ParseException e) {
			log.error("JsonParser failed to parse String to JSONObject , reason : {}", e.getMessage());
			throw new JsonParserException("failed to parse String to JSONObject",e);
		}
	}

}
