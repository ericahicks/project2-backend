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
import com.skillstorm.hotel.services.ReservationService;

@RestController 
@CrossOrigin("*")
@RequestMapping("/reservation")
public class ReservationController {
	
	private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	ReservationService service;
	
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
		return service.save(reservation);
	}
	
	// update
	@PutMapping("/{id}")
	public Reservation update(@Valid @RequestBody Reservation reservation, @PathVariable int id) {
		log.debug("Updating reservation with id=" + id + ", reservation=" + reservation);
		reservation.setId(id);
		return service.save(reservation);
	}
	
	// delete by id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		log.debug("Deleting reservation with id=" + id);
		service.deleteById(id);
	}
}
