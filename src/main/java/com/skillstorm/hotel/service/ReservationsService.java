package com.skillstorm.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.hotel.dtos.DtoUtils;
import com.skillstorm.hotel.dtos.ReservationInfoDto;
import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.repository.ReservationsRepository;

@Service
public class ReservationsService {
	

	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables ///////////////////////
	/////////////////////////////////////////////////////////////////
	
	private final ReservationsRepository reservationsRepository;
	
	private final UsersService usersService;
	

	/////////////////////////////////////////////////////////////////
	///////////////////////// Constructor ///////////////////////////
	/////////////////////////////////////////////////////////////////
	@Autowired
	public ReservationsService(ReservationsRepository reservationsRepository, 
			UsersService usersService) {
		//super();
		this.reservationsRepository = reservationsRepository;
		this.usersService = usersService;
	}
	

	/////////////////////////////////////////////////////////////////
	//////////////////////////// Methods ////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	public List<Reservations> getReservations(int page, int limit){
		return reservationsRepository.findAll(PageRequest.of(page, limit)).toList();
	}
	
	public List<Reservations> getAllReservations(){
		return reservationsRepository.findAll();
	}
	
	public Reservations getReservationsById(int id) {
		Optional<Reservations> reservations = reservationsRepository.findById(id);
		return reservations.isPresent() ? reservations.get() : null;
	}
	
	public ReservationInfoDto getReservationInfoById(int id) {
		Reservations reservation = getReservationsById(id);
		if (reservation != null) {
			return DtoUtils.create(reservation);
		}
		return null;
	}
	
	// This method gets all the reservations associated with a given user email
	public List<ReservationInfoDto> getReservationInfoByEmail(String email) {
		List<ReservationInfoDto> info = new ArrayList<>();
		// Get reservations
		List<Reservations> reservations = reservationsRepository.findByEmail(email);
		// Convert to DTOs to send back to frontend
		for (Reservations reservation : reservations) {
			info.add(DtoUtils.create(reservation));
		}
		return info;
	}
	
	// This method gets all the reservations associated with a given userid
	public List<ReservationInfoDto> getReservationInfoByUserId(String userid) {
		List<ReservationInfoDto> info = new ArrayList<>();
		// Get reservations
		List<Reservations> reservations = reservationsRepository.findByUsersId(Integer.parseInt(userid));
		// Convert to DTOs to send back to frontend
		for (Reservations reservation : reservations ) {
			info.add(DtoUtils.create(reservation));
		}
		return info;
	}
	
	
	@Transactional
	public Reservations addNewReservations(Reservations reservations) {
//		Optional<Reservations> reservationOptional = reservationsRepository.findReservationByreservationId(reservations.getReservationId());
//		if(reservationOptional.isPresent()) {
//			throw new IllegalStateException("This Reservation ID is already in use.");
//		}
		// Check if user exists and create if necessary
		HotelUsers users = reservations.getUsers();
		if (usersService.existsById(users.getId())) {
			users = usersService.updateUser(users); // update
			reservations.getUsers().setId(users.getId()); // set id in case changed
		} else {
			users = usersService.addNewUser(users); // save new
			reservations.getUsers().setId(users.getId()); // set id in case changed
		}
		if (reservationsRepository
				.existsOverlappingReservationsByRoom(reservations.getRoomnumber(), 
						                               reservations.getCheckin(), 
						                               reservations.getCheckout(), 
						                               0 // reservations.getReservationId() new so 0
						                             ) // end parameters list
			) // end if conditional statement
		{
			throw new IllegalStateException("Room " + reservations.getRoomnumber() + " is not availble the requested dates.");
		}
		return reservationsRepository.save(reservations);
	}
	
	@Transactional
	public ReservationInfoDto addNewReservations(ReservationInfoDto reservationInfo) {
		// extract data from info and create a reservation and its inner user
		Reservations reservation = DtoUtils.getReservation(reservationInfo);
		// save the reservation and its inner user to the db
		reservation = addNewReservations(reservation);
		// extract the data from the reservation and inner user to create data transfer object
		return DtoUtils.create(reservation);
	}
	
	@Transactional
	public ReservationInfoDto updateReservations(ReservationInfoDto reservationInfo) {
		
		// Step 1: Convert Dto to reservation object
		Reservations reservations = DtoUtils.getReservation(reservationInfo);
		HotelUsers users = DtoUtils.getUser(reservationInfo);
		
		// Step 2: 
			users = usersService.updateUser(users); // save or update ALWAYS
			reservations.setUsers(users); // set id in case changed
		
		// Step 3: Check if valid repository room/dates
		if (reservationsRepository
				.existsOverlappingReservationsByRoom(reservations.getRoomnumber(), 
						                               reservations.getCheckin(), 
						                               reservations.getCheckout(),
						                               reservations.getReservationId() 
						                             ) // end parameters list
			) // end if conditional statement
		{ 
			throw new IllegalStateException("Room " + reservations.getRoomnumber() + " is not availble the requested dates.");
		}
		System.out.println("=========================");
		System.out.println("Hi, I'm the Service. Saving reservation: " + reservations);
		System.out.println("    where user: " + reservations.getUsers());
		Reservations res = reservationsRepository.save(reservations);
		System.out.println("Thanks Repository!!! Saved reservation is: " + res);
		reservationInfo = DtoUtils.create(res);
		System.out.println("    Converted to DTO reservation is: " + reservationInfo);
		System.out.println("=========================");
		return reservationInfo;
	}
	
	@Transactional
	public Reservations updateReservations(Reservations reservations) {
		// update (or create if new) the user entity inside the reservation
		// set the reservation's user to the returned object incase userid changed
		reservations.setUsers(usersService.addNewUser(reservations.getUsers()));
		// save the updates to the reservation object itself
//		if (!reservationsRepository.isRoomAvailableByDates(reservations.getRoomnumber(), null, null)) {
//			throw new IllegalStateException("Room " + reservations.getRoomnumber() + " is not availble the requested dates.");
//		}
		return reservationsRepository.save(reservations);
	}
	 
	public void deleteReservation(int id) {
		if (reservationsRepository.existsById(id)) {
			reservationsRepository.deleteById(id);
		} // else your work is done!
	}
	

}
