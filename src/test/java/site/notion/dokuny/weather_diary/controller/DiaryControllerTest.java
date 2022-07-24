package site.notion.dokuny.weather_diary.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import site.notion.dokuny.weather_diary.domain.Diary;
import site.notion.dokuny.weather_diary.domain.Weather;
import site.notion.dokuny.weather_diary.service.DiaryService;


@WebMvcTest(DiaryController.class)
class DiaryControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	DiaryService diaryService;


	@Test
	void creatDiary_success() throws Exception {
		//given
		LocalDate now = LocalDate.now();

		//when
		//then
		mockMvc.perform(post("/create/diary")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString("test"))
				.param("date", now.toString()))
			.andExpect(jsonPath("$.status").value("success"));
	}

	@Test
	void creatDiary_fail() throws Exception {
		//given
		doThrow(new RuntimeException()).when(diaryService).createDiary(any(),anyString());
		//when
		//then
		mockMvc.perform(post("/create/diary")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString("test"))
				.param("date", "test"))
			.andExpect(jsonPath("$.status").value("fail"));
	}

	@Test
	void readDiary_success() throws Exception {
		//given
		Diary diary = Diary.builder()
			.date(LocalDate.of(2022,7,25))
			.text("test")
			.build();
		//when
		given(diaryService.readDiary(any()))
			.willReturn(Arrays.asList(diary));

		//then
		mockMvc.perform(get("/read/diary")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString("test"))
				.param("date", "2022-07-25"))
			.andDo(print())
			.andExpect(jsonPath("$.content[0].text").value("test"));
	}




}