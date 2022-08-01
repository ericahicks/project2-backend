package com.skillstorm.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.Rooms;
import com.skillstorm.hotel.service.RoomsService;

@RestController
@RequestMapping(path = "/Rooms")
public class RoomsController {
	
	@Autowired
	private final RoomsService roomsService;

	public RoomsController(RoomsService roomsService) {
		super();
		this.roomsService = roomsService;
	}
	
	//Method to Find All the rooms in DB and Return a List of Values
	@GetMapping
	public List<Rooms> getRooms(){
		return roomsService.getRooms();
		
	}
	
	

}
