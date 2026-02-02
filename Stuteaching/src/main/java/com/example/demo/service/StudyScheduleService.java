package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.TimeManageDTO;

public interface StudyScheduleService {

	public void InsertTime(TimeManageDTO timemanage);

	public void UpdateTime(int time_id, TimeManageDTO timemanage);

	public void DeleteTime(int time_id);

	public List<TimeManageDTO> DisplayTime(int user_id);

	TimeManageDTO findById(int timeId);

}
