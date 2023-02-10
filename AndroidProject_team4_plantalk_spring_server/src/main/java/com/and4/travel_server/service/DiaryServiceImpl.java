package com.and4.travel_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.and4.travel_server.mapper.DiaryMapper;
import com.and4.travel_server.model.Diary;

@Service
public class DiaryServiceImpl implements DiaryService {
	
	@Autowired
	private DiaryMapper diaryMapper;

	@Override
	public void insertDiary(Diary diary) {
		
		diaryMapper.insertDiary(diary);
	}

	@Override
	public List<Diary> doGetDiaryList(String trip_id) {
		return diaryMapper.doGetDiaryList(trip_id);
	}

	@Override
	public List<Diary> doGetTripDiaryList() {
		
		return diaryMapper.doGetTripDiaryList();
	}

	@Override
	public void deleteDiaryList(int dno) {
		diaryMapper.deleteDiaryList(dno);
	}

	@Override
	public void findByDno(int dno) {
		diaryMapper.findByDno(dno);
	}

	@Override
	public void diaryupdate(Diary diary) {
		diaryMapper.updateDiary(diary);
	}

	
}
