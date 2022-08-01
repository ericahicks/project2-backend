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
	
        // update
	@PutMapping("/{id}")
	public Reservation update(@Valid @RequestBody Reservation reservation, @PathVariable int id) {
		log.debug("Updating reservation with id=" + id + ", reservation=" + reservation);
		reservation.setId(id);
		// Handle the inner user object before trying to save the reservation
		// Foreign Key constraint says the user must be valid
		User user = reservation.getUser();
		if (user == null) {
			reservation.setUser(DEFAULT_USER);
		} else if (user.getId() == 0) { // no id, so create new user
			user = userService.save(user);
		} else { // has id, so assume the user exists in the db
			User oldUser = userService.findById(user.getId());
			// What if some of the fields don't match? Assume they do?
			user = oldUser;
		}
		return service.save(reservation);
	}
	
	// delete by id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		log.debug("Deleting reservation with id=" + id);
		service.deleteById(id);
	}

}
