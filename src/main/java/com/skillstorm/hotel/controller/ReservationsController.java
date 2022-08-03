package com.skillstorm.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.dtos.ReservationInfoDto;
import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.service.ReservationsService;

@RestController
@RequestMapping(path = "/reservations")
@CrossOrigin("*")
public class ReservationsController {

	/////////////////////////////////////////////////////////////////
	///////////////// Class and Instance Variables  /////////////////
	/////////////////////////////////////////////////////////////////
	private static final Logger log = LoggerFactory.getLogger(ReservationsController.class);
	

	private final ReservationsService reservationsService;

	/////////////////////////////////////////////////////////////////
	/////////////////////////// Constructor /////////////////////////
	/////////////////////////////////////////////////////////////////
	@Autowired
	public ReservationsController(ReservationsService reservationsService) {
		super();
		this.reservationsService = reservationsService;
	}

	/////////////////////////////////////////////////////////////////
	///////////////////////////// Methods ///////////////////////////
	/////////////////////////////////////////////////////////////////

	//Get All Hotel Reservations
	@GetMapping
	public List<ReservationInfoDto> getReservations(@RequestParam(defaultValue = "1") int page, 
	@RequestParam(defaultValue = "3") int limit) {
		List<ReservationInfoDto> info = new ArrayList<>();
		List<Reservations> reservations = reservationsService.getReservations();
		for (Reservations reservation : reservations) {
			info.add(new ReservationInfoDto(reservation));
		}
		return info;
	}
	
	@GetMapping("/{id}")
	public Reservations getReservationsById(@PathVariable int id) {
		return reservationsService.getReservationsById(id);
	}
	
	//This Method Adds a new Reservations To the DB
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reservations createNewReservation(@Valid @RequestBody Reservations reservations) {
		return reservationsService.addNewReservations(reservations);
	}
	
        // update
	@PutMapping("/{id}")
	public Reservations update(@Valid @RequestBody Reservations reservations, @PathVariable int id) {
		log.debug("Updating reservation with id=" + id + ", reservation=" + reservations);
		// business logic in service class to check if user in the reservation is new or old
		return reservationsService.addNewReservations(reservations);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		reservationsService.deleteReservation(id);
	}

}
