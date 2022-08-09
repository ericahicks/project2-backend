package com.skillstorm.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer>{
	
}
