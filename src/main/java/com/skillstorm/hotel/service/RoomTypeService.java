package com.skillstorm.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.dtos.DtoUtils;
import com.skillstorm.hotel.dtos.RoomTypeDto;
import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	@Autowired
	private RoomTypeRepository roomtypeRepository;
	
//	public List<RoomTypes> getRoomTypes(){
//		return roomtypeRepository.findAll();
//	}
	
	public List<RoomTypeDto> findAll() {
		List<RoomTypes> types = roomtypeRepository.findAll();
		List<RoomTypeDto> typesDto = new ArrayList<>();
		for ( RoomTypes type : types ) {
			typesDto.add(DtoUtils.create(type));
		}
		return typesDto;
	}
	
	
	
	public RoomTypeDto findById(int id) {
		Optional<RoomTypes> roomType = roomtypeRepository.findById(id);
		return roomType.isPresent() ? DtoUtils.create(roomType.get()) : null;
	}

}
