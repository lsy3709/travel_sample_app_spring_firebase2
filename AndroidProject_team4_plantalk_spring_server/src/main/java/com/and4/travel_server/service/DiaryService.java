package com.and4.travel_server.service;

import java.util.List;

import com.and4.travel_server.model.Diary;

public interface DiaryService {
	public String insertDiary(Diary diary);

	public List<Diary> doGetDiaryList();

	public List<Diary> doGetTripDiaryList();

	public void deleteDiaryList(int dno);

	public void findByDno(int dno);

	
}
