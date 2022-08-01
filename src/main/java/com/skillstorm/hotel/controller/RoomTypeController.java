package com.skillstorm.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.service.RoomTypeService;

@RestController
@RequestMapping(path = "/RoomType")
public class RoomTypeController {
	
	@Autowired
	private final RoomTypeService roomtypeService;

	public RoomTypeController(RoomTypeService roomtypeService) {
		//super();
		this.roomtypeService = roomtypeService;
	}
	
	//This Method gets all RoomTypes from DB
	@GetMapping
	public List<RoomTypes> getRoomTypes(){
		return roomtypeService.getRoomTypes();
		
	}
	
}
