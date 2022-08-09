package com.skillstorm.hotel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.RoomTypes;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypes, Integer>{
	
}
