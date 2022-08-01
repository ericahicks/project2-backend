package com.skillstorm.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.service.ReservationsService;

@RestController
@RequestMapping(path = "/Reservations")
public class ReservationsController {
	
	@Autowired
	private final ReservationsService reservationsService;
	
	public ReservationsController(ReservationsService reservationsService) {
		//super();
		this.reservationsService = reservationsService;
	}

	//Get All Hotel Reservations
	@GetMapping
	public List<Reservations> getReservations(){
		return reservationsService.getReservations();
	}
	//This Method Adds a new Reservations To the DB
	@PostMapping
	public void createNewReservation(@RequestBody Reservations reservations) {
		reservationsService.addNewReservations(reservations);
	}

}
