package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

}
