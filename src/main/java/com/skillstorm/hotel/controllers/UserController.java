package com.skillstorm.hotel.controllers;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService service;
	
	@Autowired
	ReservationService reservationService;
	
	// Find all
	@GetMapping
	public Set<User> findAll() {
		log.debug("Finding all users");
		Set<User> users = service.findAll();
		return users;
	}
	
	@GetMapping("/{userid}/reservation")
	public Set<Reservation> findReservationsByUserId(@PathVariable int userid) {
		log.debug("Finding all users");
		return service.findReservationsByUserId(userid);
	}
	
	// Find by id
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable int id) {
		log.debug("Finding user withh id=" + id);
		User user = service.findById(id);
		return new ResponseEntity<User>(user, user == null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	// Create
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user) {
		log.debug("Creating new user=" + user);
		// what to do with a nested list of reservations?
		if (user.getReservations() != null) {
			for (Reservation reservation : user.getReservations()) {
				reservationService.save(reservation); // should update if exists
				log.warn("Creating or Updating reservation=" + reservation);
			}
		}
		return service.save(user);
	}
	
	// Update
	@PutMapping("/{id}")
	public User update(@Valid @RequestBody User user, @PathVariable int id) {
		user.setId(id);
		User updatedUser = service.update(user);
		// what to do with a nested list of reservations?
		if (updatedUser.getReservations() != null) {
			
			for (Reservation reservation : updatedUser.getReservations()) {
				reservation.setId(id);
				reservationService.update(reservation); // should update if exists
				log.warn("Creating or Updating reservation=" + reservation);
			}
		}
		log.debug("Updating user with id=" + id + ", user=" + user);
		return updatedUser;
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		log.debug("Deleting user with id=" + id);
		service.delete(id);
	}
}
