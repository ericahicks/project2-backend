package com.skillstorm.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.hotel.models.Rooms;
import com.skillstorm.hotel.repository.RoomsRepository;

@Service
public class RoomsService {
	
	@Autowired
	private RoomsRepository roomsRepository;
	
	public List<Rooms> getRooms(){
		return roomsRepository.findAll();
	}
	
	public Rooms findById(int id) {
		Optional<Rooms> room = roomsRepository.findById(id);
		return room.isPresent() ? room.get() : null;
	}
	
	public Rooms findAvailableRoomByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout) {
		System.out.println("==================================");
		System.out.println("Hi, I'm the Rooms Service looking for one room available of type " + roomTypeId);
		System.out.println("     for dates: " + checkin + " to " + checkout);
		Optional<Rooms> room = roomsRepository.findAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout);
		System.out.println("Thanks repo, it looks like we've got: " + (room.isPresent() ? room.get() : "no rooms"));
		return room.isPresent() ? room.get() : null;
	}
	
	public List<Rooms> findAllAvailableRoomByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout) {
		return roomsRepository.findAllAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout);
	}
	
	public int findNumberOfRoomsAvailableByTypeAndDates(int roomTypeId, LocalDate checkin, LocalDate checkout) {
		return roomsRepository.findNumberOfRoomsAvailableByTypeAndDates(roomTypeId, checkin, checkout);
	}

}
