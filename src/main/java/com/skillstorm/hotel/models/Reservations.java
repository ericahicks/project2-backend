package com.skillstorm.hotel.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	@Column(name = "userid")
	private int	userId;
	
	@Column(name = "checkin")
	private Date checkin;
	
	@Column (name = "checkout")
	private Date checkout;
	
	
	public Reservations() {
		//super();
	}


	public Reservations(int reservationId) {
		//super();
		this.reservationId = reservationId;
	}


	public Reservations(int reservationId, int roomnumber) {
		//super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
	}


	public Reservations(int reservationId, int roomnumber, int userId) {
		//super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.userId = userId;
	}
	
	


	public Reservations(int reservationId, int roomnumber, int userId, Date checkin) {
		//super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.userId = userId;
		this.checkin = checkin;
	}


	public Reservations(int reservationId, int roomnumber, int userId, Date checkin, Date checkout) {
		//super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.userId = userId;
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


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	@Override
	public String toString() {
		return "Reservations [reservationId=" + reservationId + ", roomnumber=" + roomnumber + ", userId=" + userId
				+ ", checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
	
	
	
}
