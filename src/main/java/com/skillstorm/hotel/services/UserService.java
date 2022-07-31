package com.skillstorm.hotel.services;

import java.util.Set;

import com.skillstorm.hotel.models.Reservation;
import com.skillstorm.hotel.models.User;

public interface UserService {
	
	Set<User> findAll();
	User findById(int id);
	Set<Reservation> findReservationsByUserId(int id);
	User save(User user);
	User update(User user);
	void delete(int id);

}
