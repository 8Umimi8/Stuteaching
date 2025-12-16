package com.example.demo.service;

import com.example.demo.DTO.TimeManageDTO;

public interface StudyScheduleService {

	public void InsertTime(TimeManageDTO timemanage);

	public void UpdateTime(int time_id, TimeManageDTO timemanage);

	public void DeleteTime(int time_id);

	public TimeManageDTO DisplayTime(int user_id);

}
