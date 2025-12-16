package com.example.demo.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.TimeManageDTO;
import com.example.demo.entity.TimeEntity;
import com.example.demo.repository.StudyScheduleRepository;
import com.example.demo.service.StudyScheduleService;
import com.example.demo.util.UtilService;

@Service
public class StudyScheduleServiceImpl implements StudyScheduleService {

	@Autowired
	private StudyScheduleRepository studyschedulerepository;

	@Override
	public void InsertTime(TimeManageDTO timemanage) {
		TimeEntity entity = new TimeEntity();
		UtilService.ConvertEntity(timemanage, entity);
		studyschedulerepository.save(entity);

	}

	@Override
	public void UpdateTime(int time_id, TimeManageDTO timemanage) {
		Optional<TimeEntity> optional = studyschedulerepository.findById(time_id);
		TimeEntity entity = optional.get();

		entity.setStudy_subject(timemanage.getStudy_subject());
		entity.setStudy_time(timemanage.getStudy_time());
		entity.setDate(timemanage.getDate());

		studyschedulerepository.save(entity);

	}

	@Override
	public void DeleteTime(int time_id) {
		studyschedulerepository.deleteById(time_id);

	}

	@Override
	public TimeManageDTO DisplayTime(int user_id) {

		return null;
	}

}
