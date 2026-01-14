package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AlertEntity;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Integer> {

}
