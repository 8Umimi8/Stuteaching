package com.example.demo.util;

import org.modelmapper.ModelMapper;

public class UtilService {

	public static Object ConvertEntity(Object entity, Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(entity, dto);

		return entity;
	}

	public static Object ConvertDTO(Object entity, Object dto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(dto, entity);

		return dto;
	}
}
