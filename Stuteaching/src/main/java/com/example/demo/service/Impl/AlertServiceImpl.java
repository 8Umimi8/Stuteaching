package com.example.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AlertDTO;
import com.example.demo.entity.AlertEntity;
import com.example.demo.repository.AlertRepository;
import com.example.demo.service.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository alertrepository;

	@Override
	public List<AlertDTO> DisplayAlerts(int user_id) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<AlertEntity> alertEntity = alertrepository.findById(user_id);
		List<AlertDTO> alertDTO = new ArrayList<AlertDTO>();

		modelMapper.map(alertEntity, alertDTO);

		return alertDTO;
	}

	@Override
	public boolean DeleteAlerts(int alert_id) {
		alertrepository.deleteById(alert_id);
		return false;
	}

}
