package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeacherEntity;

@Repository
public interface Teacher extends JpaRepository<TeacherEntity, Integer> {

}
