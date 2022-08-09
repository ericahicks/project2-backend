package com.skillstorm.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.service.RoomTypeService;

@RestController
@RequestMapping(path = "/roomtypes")
public class RoomTypeController {
	
	@Autowired
	private RoomTypeService roomtypeService;
	
	//This Method gets all RoomTypes from DB
	@GetMapping
	public List<RoomTypes> getRoomTypes(){
		return roomtypeService.getRoomTypes();
	}
	
	// This method gets the details of the roomtype by id
	@GetMapping("/{id}")
	public RoomTypes findById(@PathVariable int id) {
		return roomtypeService.findById(id);
	}
	
}
