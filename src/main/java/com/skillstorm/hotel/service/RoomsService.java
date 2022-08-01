package com.skillstorm.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.Rooms;
import com.skillstorm.hotel.repository.RoomsRepository;

@Service
public class RoomsService {
	
	@Autowired
	private final RoomsRepository rooomsRepository;

	public RoomsService(RoomsRepository rooomsRepository) {
		//super();
		this.rooomsRepository = rooomsRepository;
	}
	
	public List<Rooms> getRooms(){
		return rooomsRepository.findAll();
	}
	

}
