package com.skillstorm.hotel.dtos;

import java.sql.Date;

import com.skillstorm.hotel.models.HotelUsers;
import com.skillstorm.hotel.models.Reservations;

public class ReservationInfoDto {
	
	private int reservationId;
	
	private int roomnumber;
	
	private Date checkin;
	
	private Date checkout;
	
	private int userid;
	
	private String firstName;
	
	private String lastName;
	
	private String phonenumber;
	
	private String email;

	public ReservationInfoDto() {
		super();
	}

	public ReservationInfoDto(int reservationId, int roomnumber, Date checkin, Date checkout, int userid,
			String firstName, String lastName, String phonenumber, String email) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.checkin = checkin;
		this.checkout = checkout;
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phonenumber = phonenumber;
		this.email = email;
	}
	
	public ReservationInfoDto(Reservations reservations) {
		if (reservations.getUsers() != null) {
			HotelUsers user = reservations.getUsers();
			this.userid = reservations.getUsers().getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.phonenumber = user.getPhonenumber();
			this.email = user.getEmail();
		}
		this.reservationId = reservations.getReservationId();
		this.roomnumber = reservations.getRoomnumber();
		this.checkin = reservations.getCheckin();
		this.checkout = reservations.getCheckout();
	}
	
	public void setAll(Reservations reservations) {
		if (reservations.getUsers() != null) {
			HotelUsers user = reservations.getUsers();
			this.userid = reservations.getUsers().getId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.phonenumber = user.getPhonenumber();
			this.email = user.getEmail();
		}
		this.reservationId = reservations.getReservationId();
		this.roomnumber = reservations.getRoomnumber();
		this.checkin = reservations.getCheckin();
		this.checkout = reservations.getCheckout();
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ReservationInfoDto [reservationId=" + reservationId + ", roomnumber=" + roomnumber + ", checkin="
				+ checkin + ", checkout=" + checkout + ", userid=" + userid + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phonenumber=" + phonenumber + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reservationId;
		result = prime * result + userid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationInfoDto other = (ReservationInfoDto) obj;
		if (reservationId != other.reservationId)
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}
	
}
