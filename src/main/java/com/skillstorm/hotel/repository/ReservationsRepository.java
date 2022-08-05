package com.skillstorm.hotel.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.Reservations;
@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

	Optional<Reservations> findReservationByreservationId(int reservationId);
	
	@Query(value="SELECT * FROM reservation INNER JOIN user USING (userid) "
			+ "WHERE User.email = ?1", nativeQuery = true)
	List<Reservations> findByEmail(String email);
	
	@Query("SELECT "
			+ "COUNT(*) > 0 "
			+ "FROM Reservations r "
			+ "WHERE r.roomnumber = ?1 AND r.reservationId != ?4 "
			+ "AND ((r.checkin <= ?2 AND r.checkout > ?2) OR (r.checkin >= ?2 AND r.checkin < ?3))")
	boolean existsOverlappingReservationsByRoom(int roomnumber, Date checkin, Date checkout, int reservationId);
	
	
}
