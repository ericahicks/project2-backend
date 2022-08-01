package com.skillstorm.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.hotel.models.hotelUsers;



@Repository
public interface UsersRepository extends JpaRepository<hotelUsers, Integer>{
	
	//@Query("select u from User u where u.email = ?1")
	Optional<hotelUsers> findUserByEmail(String email); 
		
	

}
