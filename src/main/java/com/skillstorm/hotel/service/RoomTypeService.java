package com.skillstorm.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	@Autowired
	private final RoomTypeRepository roomtypeRepository;

	public RoomTypeService(RoomTypeRepository roomtypeRepository) {
		//super();
		this.roomtypeRepository = roomtypeRepository;
	}
	
	public List<RoomTypes> getRoomTypes(){
		return roomtypeRepository.findAll();
	}
	

}
