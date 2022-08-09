package com.skillstorm.hotel.service;

import java.time.LocalDate;
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
	
	private final RoomsService roomsService;
	

	/////////////////////////////////////////////////////////////////
	///////////////////////// Constructor ///////////////////////////
	/////////////////////////////////////////////////////////////////
	@Autowired
	public ReservationsService(ReservationsRepository reservationsRepository, 
			UsersService usersService, RoomsService roomsService) {
		//super();
		this.reservationsRepository = reservationsRepository;
		this.usersService = usersService;
		this.roomsService = roomsService;
	}
	

	/////////////////////////////////////////////////////////////////
	//////////////////////////// Methods ////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	// Get a page of reservations containing only limit number of entries
	public List<Reservations> getReservations(int page, int limit){
		return reservationsRepository.findAll(PageRequest.of(page, limit)).toList();
	}
	
	// Get all the reservations in the database
	public List<Reservations> getAllReservations(){
		return reservationsRepository.findAll();
	}
	
	// Get the reservation with the provided id
	public Reservations getReservationsById(int id) {
		Optional<Reservations> reservations = reservationsRepository.findById(id);
		return reservations.isPresent() ? reservations.get() : null;
	}
	
	// Get the reservation info from the db with the reservationId provided
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
		// Step 1: Check if user exists and create if necessary
		HotelUsers users = reservations.getUsers();
		if (usersService.existsById(users.getId())) {
			users = usersService.updateUser(users); // update
			reservations.getUsers().setId(users.getId()); // set id in case changed
		} else {
			users = usersService.addNewUser(users); // save new
			reservations.getUsers().setId(users.getId()); // set id in case changed
		}
		
		// Step 2: Assign a roomnumber based on roomtype and checkin/checkout dates
		int roomTypeId = reservations.getRoom().getRoomType().getRoomtypeid();
		LocalDate checkin = reservations.getCheckin();
		LocalDate checkout = reservations.getCheckout();
		
		// Step 2a: Check if available rooms exist
		if (roomsService.findNumberOfRoomsAvailableByTypeAndDates(roomTypeId, checkin, checkout) == 0) {
			// throw error
			throw new IllegalStateException("No rooms of type " + reservations.getRoom().getRoomType().getName() + " are availble the requested dates.");
		} else { 
			// Step 2b: Assign the room
			int roomnumber = roomsService.findAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout).getRoomnumber();
			reservations.getRoom().setRoomnumber(roomnumber);
		}
		
		// Step 3: Save the reservation
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
		
		// Step 2: Update/save user info
			users = usersService.updateUser(users); // save or update ALWAYS
			reservations.setUsers(users); // set id in case changed
		
		// Step 3: Check if valid repository room/dates
		int roomnumber = reservations.getRoom().getRoomnumber();
		int roomTypeId = reservations.getRoom().getRoomType().getRoomtypeid();
		LocalDate checkin = reservations.getCheckin();
		LocalDate checkout = reservations.getCheckout();
		
		if (roomnumber == 0) { // the room has not been assigned, so try to assign one
			roomnumber = assignRoom(roomTypeId, checkin, checkout);
		// else the room has been assigned, check it is still available
		} else if (reservationsRepository
					.existsOverlappingReservationsByRoom(roomnumber, checkin, checkout, 
						                               reservations.getReservationId() ) ) {
			// Try to reassign room 
			roomnumber = assignRoom(roomTypeId, checkin, checkout);
		} // else 
		// the room is still available so continue
		
		// Step 4: Save the updated registration info
		System.out.println("=========================");
		System.out.println("Hi, I'm the Service. Saving reservation: " + reservations);
		System.out.println("    where user: " + reservations.getUsers());
		Reservations res = reservationsRepository.save(reservations);
		System.out.println("Thanks Repository! Saved reservation is: " + res);
		reservationInfo = DtoUtils.create(res);
		System.out.println("    Converted to DTO reservation is: " + reservationInfo);
		System.out.println("=========================");
		return reservationInfo;
	}
	
	private int assignRoom(int roomTypeId, LocalDate checkin, LocalDate checkout) {
		if (roomsService.findNumberOfRoomsAvailableByTypeAndDates(roomTypeId, checkin, checkout) == 0) {
			// throw error
			throw new IllegalStateException("No rooms of the selected type are availble the requested dates.");
		} else { // assign room
			return roomsService.findAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout).getRoomnumber();
		}
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
		System.out.println("-----------------------------------");
		System.out.println("Hi, I'm the Service, here to delete our reservation #" + id);
		if (reservationsRepository.existsById(id)) {
			System.out.println("Yup, I see that reservation exists. Let me just obliterate it's existance for you. No problem.");
			reservationsRepository.deleteById(id);
//			if (reservationsRepository.existsById(id)) {
//				System.out.println("Uhm... about that. Something clearly went wrong. Uh that reservation you didn't like. It's still here. Ya sorry bout that.");
//			}
		} // else your work is done!
	}
	
	public List<ReservationInfoDto> findByRoomnumber(int roomnumber) {
		List<Reservations> reservations = reservationsRepository.findByRoomnumber(roomnumber);
		List<ReservationInfoDto> info = new ArrayList<>();
		for (Reservations reservation : reservations) {
			info.add(DtoUtils.create(reservation));
		}
		return info;
	}
	

}
