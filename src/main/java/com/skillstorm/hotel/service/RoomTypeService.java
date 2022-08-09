package com.skillstorm.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	@Autowired
	private RoomTypeRepository roomtypeRepository;

	public List<RoomTypes> getRoomTypes(){
		return roomtypeRepository.findAll();
	}
	
	public RoomTypes findById(int id) {
		Optional<RoomTypes> roomType = roomtypeRepository.findById(id);
		return roomType.isPresent() ? roomType.get() : null;
	}

}
