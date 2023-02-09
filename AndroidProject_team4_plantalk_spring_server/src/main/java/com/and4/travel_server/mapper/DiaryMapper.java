package com.and4.travel_server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.and4.travel_server.model.Diary;

@Mapper
public interface DiaryMapper {
	
	public void insertDiary(Diary diary);
	
	public List<Diary> doGetDiaryList(String tip_id);
	
	public List<Diary> doGetTripDiaryList();
	
	public void deleteDiaryList(int dno);
	
	public void findByDno(int dno);
	

}
