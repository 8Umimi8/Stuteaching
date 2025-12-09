package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AlertDTO;
import com.example.demo.repository.Alert;
import com.example.demo.service.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private Alert alertrepository;

	@Override
	public List<AlertDTO> DisplayAlerts(int user_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean DeleteAlerts(int alert_id) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
