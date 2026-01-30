package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.TalkEntity;

@Repository
public interface TalkRepository extends JpaRepository<TalkEntity, Integer> {
	List<TalkEntity> findByRoomIdOrderByCreatedAtAsc(RoomEntity room);

}
