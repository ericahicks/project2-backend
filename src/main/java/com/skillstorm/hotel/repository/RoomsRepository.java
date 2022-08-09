package com.skillstorm.hotel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer>{
	
	@Query(value = " SELECT * "
			+ " FROM room "
			+ " where roomtypeid = ?1 AND roomnumber  " // ?1 is desired roomtypeid
			+ "  NOT IN ( "
			+ "			SELECT  "
			+ "			room.roomnumber "
			+ "			from reservation "
			+ "			   left join room on reservation.roomnumber = room.roomnumber "
			+ "			where ((reservation.checkout <= ?2) OR (reservation.checkin >= ?3)) = 0 "
			+ "		) "
			+ " order by roomnumber "
			+ " limit 1", nativeQuery = true)
	Optional<Rooms> findAvailableRoomByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout);
	
	@Query(value = " SELECT * "
			+ " FROM room "
			+ " where roomtypeid = ?1 AND roomnumber  " // ?1 is desired roomtypeid
			+ "  NOT IN ( "
			+ "			SELECT  "
			+ "			room.roomnumber "
			+ "			from reservation "
			+ "			   left join room on reservation.roomnumber = room.roomnumber "
			+ "			where ((reservation.checkout <= ?2) OR (reservation.checkin >= ?3)) = 0 "
			+ "		) "
			+ " order by roomnumber ", nativeQuery = true)
	List<Rooms> findAllAvailableRoomByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout);
	
	
	@Query(value = " SELECT COUNT(*) "
			+ " FROM room "
			+ " where roomtypeid = ?1 AND roomnumber  " // ?1 is desired roomtypeid
			+ "  NOT IN ( "
			+ "			SELECT  "
			+ "			room.roomnumber "
			+ "			from reservation "
			+ "			   left join room on reservation.roomnumber = room.roomnumber "
			+ "			where ((reservation.checkout <= ?2) OR (reservation.checkin >= ?3)) = 0 "
			+ "		) ", nativeQuery = true)
	int findNumberOfRoomsAvailableByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout);
}
