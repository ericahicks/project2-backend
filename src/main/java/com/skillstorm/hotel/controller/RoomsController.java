package com.skillstorm.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.hotel.dtos.RoomSearchParamsDto;
import com.skillstorm.hotel.models.Rooms;
import com.skillstorm.hotel.service.RoomsService;

@RestController
@RequestMapping(path = "/rooms")
public class RoomsController {
	
	@Autowired
	private RoomsService roomsService;

	//Method to Find All the rooms in DB and Return a List of Values
	@GetMapping
	public List<Rooms> getRooms(){
		return roomsService.getRooms();
	}
	
	// Method to get room details by roomnumber
	@GetMapping("/{roomnumber}")
	public ResponseEntity<Rooms> findById(@PathVariable int roomnumber) {
		Rooms room = roomsService.findById(roomnumber);
		return new ResponseEntity<>(room, room == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	// Gets the first room number in a list of available rooms of the given type on the given dates
	@PostMapping("/available")
	public ResponseEntity<Integer> findAvailableRoomByTypeAndDates(@RequestBody RoomSearchParamsDto searchParams) {
		int roomTypeId = searchParams.getRoomTypeId();
		LocalDate checkin = searchParams.getCheckin();
		LocalDate checkout = searchParams.getCheckout();
		System.out.println("==================================");
		System.out.println("Hi, I'm the Rooms Controller looking for one room available of type " + roomTypeId);
		System.out.println("     for dates: " + checkin + " to " + checkout);
		
		Rooms room = roomsService.findAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout);

		System.out.println("Thanks service, it looks like we've got: " + (room == null ? room : "no rooms"));
		Integer roomnumber = room == null ? 0 : room.getRoomnumber();
		System.out.println("      I'm returning roomnumber: " + roomnumber);

		return new ResponseEntity<>(roomnumber, roomnumber == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping("/available/all")
	public ResponseEntity<List<Rooms>> findAllAvailableRoomByTypeAndDates(@RequestBody RoomSearchParamsDto searchParams) {
		int roomTypeId = searchParams.getRoomTypeId();
		LocalDate checkin = searchParams.getCheckin();
		LocalDate checkout = searchParams.getCheckout();
		List<Rooms> rooms = roomsService.findAllAvailableRoomByTypeAndDates(roomTypeId, checkin, checkout);
		return new ResponseEntity<>(rooms, rooms != null && rooms.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/available/count")
	public int findNumberOfRoomsAvailableByTypeAndDates(@RequestBody RoomSearchParamsDto searchParams) {
		int roomTypeId = searchParams.getRoomTypeId();
		LocalDate checkin = searchParams.getCheckin();
		LocalDate checkout = searchParams.getCheckout();
		int numberOfRooms = roomsService.findNumberOfRoomsAvailableByTypeAndDates(roomTypeId, checkin, checkout);
		return numberOfRooms;
	}
	
	
}
