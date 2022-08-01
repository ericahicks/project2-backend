package com.skillstorm.hotel.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reservation")
public class Reservations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationid")
	private int reservationId;
	
	@Column(name = "roomnumber")
	private int roomnumber;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private hotelUsers users;
	
	
	@Column(name = "checkin")
	private Date checkin;
	
	@Column (name = "checkout")
	private Date checkout;
	
	

	public Reservations() {
		super();
	}



	public Reservations(int reservationId) {
		super();
		this.reservationId = reservationId;
	}



	public Reservations(int reservationId, int roomnumber) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
	}



	public Reservations(int reservationId, int roomnumber, hotelUsers users) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.users = users;
	}



	public Reservations(int reservationId, int roomnumber, hotelUsers users, Date checkin) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.users = users;
		this.checkin = checkin;
	}



	public Reservations(int reservationId, int roomnumber, hotelUsers users, Date checkin, Date checkout) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.users = users;
		this.checkin = checkin;
		this.checkout = checkout;
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



	public hotelUsers getUsers() {
		return users;
	}



	public void setUsers(hotelUsers users) {
		this.users = users;
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



//	@Override
//	public String toString() {
//		return "Reservations [reservationId=" + reservationId + ", roomnumber=" + roomnumber + ", users=" + users
//				+ ", checkin=" + checkin + ", checkout=" + checkout + "]";
//	}
	
	
	
	
	
	
	
	
}
