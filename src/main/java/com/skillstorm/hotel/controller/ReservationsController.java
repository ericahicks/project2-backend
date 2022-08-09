package com.skillstorm.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.dtos.DtoUtils;
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
	
	@Autowired
	private ReservationsService reservationsService;

	/////////////////////////////////////////////////////////////////
	///////////////////////////// Methods ///////////////////////////
	/////////////////////////////////////////////////////////////////

	//Get a page of Hotel Reservations
	@GetMapping
	public List<ReservationInfoDto> getReservations(@RequestParam(defaultValue = "1") int page, 
	@RequestParam(defaultValue = "3") int limit) {
		List<ReservationInfoDto> info = new ArrayList<>();
		List<Reservations> reservations = reservationsService.getReservations(page, limit);
		for (Reservations reservation : reservations) {
			info.add(DtoUtils.create(reservation));
		}
		return info;
	}
	
	//Get All Hotel Reservations
	@GetMapping("/all")
	public List<ReservationInfoDto> getAllReservations() {
		List<ReservationInfoDto> info = new ArrayList<>();
		List<Reservations> reservations = reservationsService.getAllReservations();
		for (Reservations reservation : reservations) {
			info.add(DtoUtils.create(reservation));
		}
		return info;
	}
	
	@GetMapping("/room/{number}")
	public ResponseEntity<List<ReservationInfoDto>> findByRoomnumber(@PathVariable int number) {
		List<ReservationInfoDto> reservations = reservationsService.findByRoomnumber(number);
		return new ResponseEntity<>(reservations, reservations != null && reservations.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	// Get all reservations for the user with an id of userid
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<ReservationInfoDto>> getUsersReservationsByUserId(@PathVariable String userid) {
		List<ReservationInfoDto> reservations = reservationsService.getReservationInfoByUserId(userid);
		return new ResponseEntity<>(reservations, reservations.size() == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}
	
	// Get all reservations for the user with the email contained in the body
	// Get method using post bc email in url is weird
	@PostMapping("/user")
	public ResponseEntity<List<ReservationInfoDto>> getUsersReservations(@RequestBody String body) {
		System.out.println("Request Body was: " + body);
		List<ReservationInfoDto> reservations = reservationsService.getReservationInfoByEmail(body);
		return new ResponseEntity<>(reservations, reservations.size() > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	// Get the details of the reservation with the provided id number
	@GetMapping("/{id}")
	public ResponseEntity<ReservationInfoDto> getReservationsById(@PathVariable int id) {
		ReservationInfoDto reservation = reservationsService.getReservationInfoById(id);
		if (reservation == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	// Create a reservation from the provided reservation info
	@PostMapping // TODO add @Valid
	public ResponseEntity<ReservationInfoDto> createNewReservation(@RequestBody ReservationInfoDto info) {
		System.out.println("=================================");
		System.out.println("Hi, I'm the Reservation Controller.I will create a new reservation: " + info);
		System.out.println("=================================");
		info = reservationsService.addNewReservations(info); // save response (should have updated reservationid and maybe userid)
		return new ResponseEntity<ReservationInfoDto>(info, info == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED);
	}
	
	// Update the reservation with the provided id
	@PutMapping("/{id}") // TODO add @Valid
	@CrossOrigin("*")	
	public ResponseEntity<ReservationInfoDto> updateReservation(@RequestBody ReservationInfoDto info,  @PathVariable int id) {

		System.out.println("=================================");
		System.out.println("Hi, I'm the Controller. Updating the reservation: " + info);
		System.out.println("=================================");
		info = reservationsService.updateReservations(info); // save response (should have updated reservationid and maybe userid)
		return new ResponseEntity<ReservationInfoDto>(info, info == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.CREATED);
	}
	
	// Delete the reservation with the provided id number
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		System.out.println("========================================================");
		System.out.println("Hi, I'm the Controller, here to delete your reservation #" + id);
		reservationsService.deleteReservation(id);
	}

}
