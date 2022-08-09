package com.skillstorm.hotel.dtos;

import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.models.Reservations;
import com.skillstorm.hotel.models.RoomTypes;
import com.skillstorm.hotel.models.Rooms;

public class DtoUtils {
	
	////////////////////////////////////////////////////////////////////
	////////////////// ReservationInfoDto Utilities ////////////////////
	////////////////////////////////////////////////////////////////////

	public static ReservationInfoDto create(Reservations reservations) {
		ReservationInfoDto info = new ReservationInfoDto();
		if (reservations.getUsers() != null) {
			HotelUsers user = reservations.getUsers();
			info.setUserid(reservations.getUsers().getId());
			info.setFirstName(user.getFirstName());
			info.setLastName(user.getLastName());
			info.setPhonenumber(user.getPhonenumber());
			info.setEmail(user.getEmail());
		}
		if (reservations.getRoom() != null) {
			Rooms room = reservations.getRoom();
			RoomTypes roomType = room.getRoomType();
			info.setRoomnumber(room.getRoomnumber());
			info.setRoomTypeId(roomType.getRoomtypeid());
			info.setRoomTypeName(roomType.getName());
			info.setPrice(roomType.getPrice());
		}
		info.setReservationId(reservations.getReservationId());
		info.setCheckin(reservations.getCheckin());
		info.setCheckout(reservations.getCheckout());
		return info;
	}
	
	public static Reservations getReservation(ReservationInfoDto info) {
		// RoomType may have changed
		RoomTypes roomType = new RoomTypes(info.getRoomTypeId(), info.getRoomTypeName(), info.getPrice());
		// If roomnumber is unset this means roomtype changed and room must be assigned again later not here
		Rooms room = new Rooms(info.getRoomnumber(), roomType, null);
		// User constructor using all fields except reservation list
		// params (int id, String firstName, String lastName, String number, String email) {
		HotelUsers user = new HotelUsers(info.getUserid(), info.getFirstName(), info.getLastName(), info.getPhonenumber(), info.getEmail());
		// constructor that uses all fields except id 
		// params (int roomnumber, HotelUsers users, Date checkin, Date checkout)
		return new Reservations(room, user, info.getCheckin(), info.getCheckout());
	}
	
	public static HotelUsers getUser(ReservationInfoDto info) {
		return new HotelUsers(info.getUserid(), info.getFirstName(), info.getLastName(), info.getPhonenumber(), info.getEmail());
	}
	
	public static RoomTypes getRoomType(ReservationInfoDto info) {
		return new RoomTypes(info.getRoomTypeId(), info.getRoomTypeName(), info.getPrice());
	}
	
	public static Rooms getRoom(ReservationInfoDto info) {
		RoomTypes roomType = getRoomType(info);
		return new Rooms(info.getRoomnumber(), roomType, null);
	}
	
}
