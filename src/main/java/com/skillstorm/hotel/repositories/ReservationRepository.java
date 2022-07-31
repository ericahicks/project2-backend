package com.skillstorm.hotel.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillstorm.hotel.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query("select r from reservation r where checkin >= ?1")
	public List<Reservation> findAllAfter(Date date);
}
