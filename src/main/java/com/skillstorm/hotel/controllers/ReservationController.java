package com.skillstorm.hotel.controllers;

import java.sql.Date;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.models.Reservation;
import com.skillstorm.hotel.models.User;
import com.skillstorm.hotel.services.ReservationService;
import com.skillstorm.hotel.services.UserService;

@RestController 
@CrossOrigin("*")
@RequestMapping("/reservations")
public class ReservationController {
	
	private static final Logger log = LoggerFactory.getLogger(ReservationController.class);
	private static final User DEFAULT_USER = new User(1, "No", "User", "+0 (000) 000-0000", "no@user.com", null);

	@Autowired
	ReservationService service;
	
	@Autowired
	UserService userService;
	
	// find all
	@GetMapping
	public Set<Reservation> findAll() {
		log.debug("Finding all reservations.");
		// FOR TESTING PURPOSES ONLY
//		Set<Reservation> revs = new HashSet<>();
//		revs.add(new Reservation(1, 101, null, Date.valueOf("2022-08-09"), Date.valueOf("2022-08-12")));
		Set<Reservation> revs = service.findAll();
		return revs;
	}
	
	// find all future
	@GetMapping("/upcoming")
	public Set<Reservation> findAllUpcoming() {
		log.debug("Finding all future reservations.");
		long localTime = java.time.Instant.now().toEpochMilli();
		Set<Reservation> revs = service.findAllAfter(new Date(localTime));
		return revs;
	}
	
	// find by id
	@GetMapping("/{id}")
	public Reservation findById(@PathVariable int id) {
		log.debug("Finding reservation by id=" + id);
		Reservation res = service.findById(id);
		return res;
	}
	
	// create
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation create(@Valid @RequestBody Reservation reservation) {
		log.debug("Creating reservations=" + reservation);
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
