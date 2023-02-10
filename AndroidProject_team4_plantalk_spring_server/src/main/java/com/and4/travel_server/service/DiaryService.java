package com.and4.travel_server.service;

import java.util.List;

import com.and4.travel_server.model.Diary;

public interface DiaryService {
	public void insertDiary(Diary diary);

	public List<Diary> doGetDiaryList(String tip_id);

	public List<Diary> doGetTripDiaryList();

	public void deleteDiaryList(int dno);
	
	public void diaryupdate(Diary diary);

	public void findByDno(int dno);

	
}
