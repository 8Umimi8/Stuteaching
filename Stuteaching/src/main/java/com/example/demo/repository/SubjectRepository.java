package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
}