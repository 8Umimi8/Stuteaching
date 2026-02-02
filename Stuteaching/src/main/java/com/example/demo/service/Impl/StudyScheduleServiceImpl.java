package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
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
		UtilService.ConvertEntity(entity, timemanage);
		studyschedulerepository.save(entity);

	}

	@Override
	public void UpdateTime(int time_id, TimeManageDTO timemanage) {
		Optional<TimeEntity> optional = studyschedulerepository.findById(time_id);
		TimeEntity entity = optional.get();

		entity.setStudySubject(timemanage.getStudySubject());
		entity.setStudyTime(timemanage.getStudyTime());
		entity.setDate(timemanage.getDate());

		studyschedulerepository.save(entity);

	}

	@Override
	public void DeleteTime(int time_id) {
		studyschedulerepository.deleteById(time_id);

	}

	@Override
	public List<TimeManageDTO> DisplayTime(int userId) {

		List<TimeEntity> entityList = studyschedulerepository.findByUserId(userId);

		List<TimeManageDTO> dtoList = new ArrayList<>();

		for (TimeEntity entity : entityList) {
			TimeManageDTO dto = new TimeManageDTO();
			UtilService.ConvertDTO(entity, dto);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public TimeManageDTO findById(int timeId) {

		TimeEntity entity = studyschedulerepository.findById(timeId)
				.orElseThrow(() -> new RuntimeException("データが存在しません"));

		TimeManageDTO dto = new TimeManageDTO();
		UtilService.ConvertDTO(entity, dto);

		return dto;
	}

}
