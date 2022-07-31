package com.skillstorm.hotel.controllers;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	// Find all
	@GetMapping
	public Set<User> findAll() {
		log.debug("Finding all users");
		Set<User> users = new HashSet<>();
		users.add(new User(1, "Clark", "Kent", "1-234-555-6789", "superman@gmail.com", null));
		return users;
	}
	
	@GetMapping("/{userid}/reservation")
	public Set<Reservation> findReservationsByUserId(@PathVariable int userid) {
		log.debug("Finding all users");
		Set<Reservation> revs = new HashSet<>();
		revs.add(new Reservation(1, 101, null, Date.valueOf("2022-08-09"), Date.valueOf("2022-08-12")));
		return revs;
	}
	
	// Find by id
	@GetMapping("/{id}")
	public User findById(@PathVariable int id) {
		log.debug("Finding user withh id=" + id);
		return new User(1, "Clark", "Kent", "1-234-555-6789", "superman@gmail.com", null);
	}
	// Create
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user) {
		log.debug("Creating new user=" + user);
		return user;
	}
	
	// Update
	@PutMapping("/{id}")
	public User update(@Valid @RequestBody User user, @PathVariable int id) {
		user.setId(id);
		log.debug("Updating user with id=" + id + ", user=" + user);
		return user;
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		log.debug("Deleting user with id=" + id);
	}
}
