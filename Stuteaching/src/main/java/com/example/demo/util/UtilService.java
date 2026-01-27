package com.example.demo.util;

import org.modelmapper.ModelMapper;

import com.example.demo.DTO.TalkDTO;
import com.example.demo.entity.TalkEntity;

public class UtilService {

	private static final ModelMapper mapper = new ModelMapper();

	static {
		mapper.typeMap(TalkDTO.class, TalkEntity.class)
				.addMappings(m -> m.skip(TalkEntity::setClassId));
	}

	// DTO → Entity
	public static void ConvertEntity(Object entity, Object dto) {
		mapper.map(dto, entity);
	}

	// Entity → DTO
	public static void ConvertDTO(Object entity, Object dto) {
		mapper.map(entity, dto);
	}
}