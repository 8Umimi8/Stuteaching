package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AlertEntity;

import jakarta.transaction.Transactional;

@Repository
public interface Alert extends JpaRepository<AlertEntity, Integer> {

	@Query("SELECT a FROM AlertEntity a WHERE a.alert_id = :alert_id")
	List<AlertEntity> DisplayAlerts(@Param("alert_id") int alert_id);

	@Modifying
	@Transactional
	@Query("DELETE FROM AlertEntity a WHERE a.alert_id IN :alert_id")
	int DeleteAlerts(@Param("alert_id") int[] alert_id);
}
