package com.skillstorm.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.repository.ReservationsRepository;

@Service
public class ReservationsService {
	
	@Autowired
	private final ReservationsRepository reservationsRepository;

	public ReservationsService(ReservationsRepository reservationsRepository) {
		//super();
		this.reservationsRepository = reservationsRepository;
	}
	
	public List<Reservations> getReservations(){
		return reservationsRepository.findAll();
	}
	
	public void addNewReservations(Reservations reservations) {
		Optional<Reservations> reservationOptional = reservationsRepository.findReservationByreservationId(reservations.getReservationId());
		if(reservationOptional.isPresent()) {
			throw new IllegalStateException("This Reservation ID is already in use.");
		}
		reservationsRepository.save(reservations);
	}
	
	

}
