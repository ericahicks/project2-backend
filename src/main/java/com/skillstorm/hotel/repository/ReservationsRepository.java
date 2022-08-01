package com.skillstorm.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.hotel.models.Reservations;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

	Optional<Reservations> findReservationByreservationId(int reservationId);
}
