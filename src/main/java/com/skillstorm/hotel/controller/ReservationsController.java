package com.skillstorm.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.models.hotelUsers;
import com.skillstorm.hotel.service.ReservationsService;
import com.skillstorm.hotel.service.UsersService;

@RestController
@RequestMapping(path = "/Reservations")
public class ReservationsController {
	private static final Logger log = LoggerFactory.getLogger(ReservationsController.class);
	
	@Autowired
	private final ReservationsService reservationsService;
	@Autowired
	private final UsersService usersService;
	


	public ReservationsController(ReservationsService reservationsService, UsersService usersService) {
		super();
		this.reservationsService = reservationsService;
		this.usersService = usersService;
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
	
        // update
	@PutMapping("/{id}")
	public Reservations update(@Valid @RequestBody Reservations reservations, @PathVariable int id) {
		log.debug("Updating reservation with id=" + id + ", reservation=" + reservations);
		reservations.setUsers(id);
		// Handle the inner user object before trying to save the reservation
		// Foreign Key constraint says the user must be valid
		hotelUsers user = reservations.getUsers();
		 if (user.getId() == 0) { // no id, so create new user
			user = usersService.addNewUser(user);
		} else { // has id, so assume the user exists in the db
			usersService.updateUser(user);
		}
		return reservationsService.addNewReservations(reservations);
	}
	
	// delete by id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		log.debug("Deleting reservation with id=" + id);
		reservationsService.deleteReservation(id);	}

}
