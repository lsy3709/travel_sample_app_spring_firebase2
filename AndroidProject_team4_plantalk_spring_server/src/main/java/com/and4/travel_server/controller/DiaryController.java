package com.and4.travel_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.and4.travel_server.model.Diary;
import com.and4.travel_server.service.DiaryService;

@RestController
@RequestMapping("/travel/")
public class DiaryController {
	
	@Autowired
	private DiaryService diaryService;

	
	@PostMapping("diaryinsert")
	public void insertDiary(@RequestBody Diary diary) {
//		System.out.println("diary.getTitle(): "+diary.getTitle());
//		System.out.println("diary.getTrip_id(): "+diary.getTrip_id());
		System.out.println("diary.getImage_uri(): "+diary.getImage_uri().length());
		System.out.println("diary.getVideo_uri(): "+diary.getVideo_uri().length());
		
		diaryService.insertDiary(diary);
		//return "datasave";
	}
	
	@GetMapping("diaryList/{trip_id}")
	public Map<String,List<Diary>> doGetDiaryList(@PathVariable("trip_id") String trip_id){
		Map<String,List<Diary>> map = new HashMap<String,List<Diary>>();
		map.put("diarys",diaryService.doGetDiaryList(trip_id));
		return map;
	}
	
	@GetMapping("tripDiaryList")
	public Map<String,List<Diary>> doGetTripDiaryList(){
		Map<String,List<Diary>> map = new HashMap<String,List<Diary>>();
		map.put("diarys",diaryService.doGetTripDiaryList());
		return map;
	}
	//diaryupdate
	@PostMapping("diaryupdate")
	public void diaryupdate(@RequestBody Diary diary) {
		diaryService.diaryupdate(diary);
	}
	
	@PostMapping("diaryListDelete/{dno}")
	public void deleteDiaryList(@PathVariable("dno") int dno) {
		diaryService.deleteDiaryList(dno);
	}
	
	
	@GetMapping("getDiary/{dno}")
	public void getDiary(@PathVariable("dno") int dno) {
		
	}
	
}
