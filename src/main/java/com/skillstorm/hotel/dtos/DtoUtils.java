package com.skillstorm.hotel.dtos;

import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.models.Reservations;

public class DtoUtils {

	

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
		info.setReservationId(reservations.getReservationId());
		info.setRoomnumber(reservations.getRoomnumber());
		info.setCheckin(reservations.getCheckin());
		info.setCheckout(reservations.getCheckout());
		return info;
	}
	
	public static Reservations getReservation(ReservationInfoDto info) {
		// User constructor using all fields except reservation list
		// params (int id, String firstName, String lastName, String number, String email) {
		HotelUsers user = new HotelUsers(info.getUserid(), info.getFirstName(), info.getLastName(), info.getPhonenumber(), info.getEmail());
		// constructor that uses all fields except id 
		// params (int roomnumber, HotelUsers users, Date checkin, Date checkout)
		return new Reservations(info.getReservationId(), info.getRoomnumber(), user, info.getCheckin(), info.getCheckout());
	}
	
	public static HotelUsers getUser(ReservationInfoDto info) {
		return new HotelUsers(info.getUserid(), info.getFirstName(), info.getLastName(), info.getPhonenumber(), info.getEmail());
	}
}
