package site.notion.dokuny.weather_diary.controller;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.notion.dokuny.weather_diary.domain.Diary;
import site.notion.dokuny.weather_diary.dto.ResponseResult;
import site.notion.dokuny.weather_diary.service.DiaryService;

@RequiredArgsConstructor
@RestController
public class DiaryController {

	private final DiaryService diaryService;

	@PostMapping("/create/diary")
	public ResponseEntity createDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,@RequestBody String text) {
		diaryService.createDiary(date, text);
		return ResponseEntity.ok().body(ResponseResult.builder()
			.status(ResponseResult.SUCCESS).build());
	}

	@GetMapping("/read/diary")
	public ResponseEntity readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		return ResponseEntity.ok()
			.body(ResponseResult.<List<Diary>>builder()
				.content(diaryService.readDiary(date))
				.status(ResponseResult.SUCCESS).build());
	}

	@GetMapping("/read/diaries")
	public ResponseEntity readDiaries(
		@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
		@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
		return ResponseEntity.ok()
			.body(ResponseResult.<List<Diary>>builder()
				.content(diaryService.readDiaries(startDate, endDate))
				.status(ResponseResult.SUCCESS).build());
	}

	@PutMapping("/update/diary")
	public ResponseEntity updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,@RequestBody String text) {
		diaryService.updateDiary(date,text);
		return ResponseEntity.ok().body(ResponseResult.builder()
			.status(ResponseResult.SUCCESS).build());
	}

	@DeleteMapping("/delete/diary")
	public ResponseEntity deleteDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		diaryService.deleteDiary(date);
		return ResponseEntity.ok().body(ResponseResult.builder()
			.status(ResponseResult.SUCCESS).build());
	}
}
