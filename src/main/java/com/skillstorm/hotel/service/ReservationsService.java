package com.skillstorm.hotel.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.hotel.dtos.ReservationInfoDto;
import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.repository.ReservationsRepository;
import com.skillstorm.hotel.repository.UsersRepository;

@Service
public class ReservationsService {
	

	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables ///////////////////////
	/////////////////////////////////////////////////////////////////
	
	private final ReservationsRepository reservationsRepository;
	
	private final UsersRepository usersRepository;
	

	/////////////////////////////////////////////////////////////////
	///////////////////////// Constructor ///////////////////////////
	/////////////////////////////////////////////////////////////////
	@Autowired
	public ReservationsService(ReservationsRepository reservationsRepository, 
			UsersRepository usersRepository) {
		//super();
		this.reservationsRepository = reservationsRepository;
		this.usersRepository = usersRepository;
	}
	

	/////////////////////////////////////////////////////////////////
	//////////////////////////// Methods ////////////////////////////
	/////////////////////////////////////////////////////////////////
	
	public List<Reservations> getReservations(){
		return reservationsRepository.findAll();
	}
	
	public Reservations getReservationsById(int id) {
		Optional<Reservations> reservations = reservationsRepository.findById(id);
		return reservations.isPresent() ? reservations.get() : null;
	}
	
	public ReservationInfoDto getReservationInfoById(int id) {
		ReservationInfoDto info = new ReservationInfoDto();
		Reservations reservation = getReservationsById(id);
		if (reservation != null) {
			info.setAll(reservation);
		}
		return info;
	}
	
	@Transactional
	public Reservations addNewReservations(Reservations reservations) {
		Optional<Reservations> reservationOptional = reservationsRepository.findReservationByreservationId(reservations.getReservationId());
		if(reservationOptional.isPresent()) {
			throw new IllegalStateException("This Reservation ID is already in use.");
		}
		// Check if user exists and create if necessary
		HotelUsers users = reservations.getUsers();
		if (usersRepository.existsById(users.getId())) {
			users = usersRepository.save(users); // save or update
			reservations.getUsers().setId(users.getId()); // set id in case changed
		}
		if (reservationsRepository
				.numberOfOverlappingReservationsByRoom(reservations.getRoomnumber(), 
						                               reservations.getCheckin(), 
						                               reservations.getCheckout() ) > 0) {
			throw new IllegalStateException("Room " + reservations.getRoomnumber() + " is not availble the requested dates.");
		}
		return reservationsRepository.save(reservations);
	}
	
	@Transactional
	public Reservations updateReservations(Reservations reservations) {
		// update (or create if new) the user entity inside the reservation
		// set the reservation's user to the returned object incase userid changed
		reservations.setUsers(usersRepository.save(reservations.getUsers()));
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
