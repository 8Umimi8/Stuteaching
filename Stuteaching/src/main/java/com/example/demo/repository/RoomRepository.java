package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.RoomEntity;
import com.example.demo.entity.UserEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

	Optional<RoomEntity> findByRoomTeacherIdAndRoomUserIdAndIsDeletedFalse(
			UserEntity teacher,
			UserEntity user);

	@Query("""
			SELECT r
			FROM RoomEntity r
			WHERE (r.roomUserId.user_id = :userId
			    OR r.roomTeacherId.user_id = :userId)
			AND r.isDeleted = false
			""")
	List<RoomEntity> findRoomsByUserId(Integer userId);

}
