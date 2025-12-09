package com.example.demo.service;

import java.util.List;

import com.example.demo.DTO.AlertDTO;

public interface AlertService {

	public List<AlertDTO> DisplayAlerts(int user_id);

	public boolean DeleteAlerts(int alert_id);

}
