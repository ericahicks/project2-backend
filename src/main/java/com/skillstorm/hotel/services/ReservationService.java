package com.skillstorm.hotel.services;

import java.sql.Date;
import java.util.Set;

import com.skillstorm.hotel.models.Reservation;

public interface ReservationService {
	
	Set<Reservation> findAll();
	Set<Reservation> findAllAfter(Date date);
	Reservation findById(int id);
	Reservation save(Reservation reservation);
	Reservation update(Reservation reservation);
	void deleteById(int id);

}
