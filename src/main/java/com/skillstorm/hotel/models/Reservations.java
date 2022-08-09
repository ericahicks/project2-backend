package com.skillstorm.hotel.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;


@Entity
@Table(name = "reservation")
public class Reservations {
	
	private static final int MIN_ROOMNUMBER = 100;
	private static final int MAX_ROOMNUMBER = 520;
	
	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables  //////////////////////
	/////////////////////////////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationid")
	private int reservationId;
	
	@Column(name = "roomnumber")
	@Min(MIN_ROOMNUMBER)
	@Max(MAX_ROOMNUMBER)
	private int roomnumber;
	
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonIdentityReference(alwaysAsId = true)
	private HotelUsers users;
	
	@Column(name = "checkin")
	@NotNull
	private LocalDate checkin;
	
	@Column (name = "checkout")
	@NotNull
	private LocalDate checkout;

	/////////////////////////////////////////////////////////////////
	/////////////////////////// Constructors ////////////////////////
	/////////////////////////////////////////////////////////////////

	// No args constructor
	public Reservations() {
		super();
	}

	// Constructor using all the fields
	public Reservations(int reservationId, int roomnumber, HotelUsers users, LocalDate checkin, LocalDate checkout) {
		super();
		this.reservationId = reservationId;
		this.roomnumber = roomnumber;
		this.users = users;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	// Constructor using all the fields except id
	public Reservations(int roomnumber, HotelUsers users, LocalDate checkin, LocalDate checkout) {
		super();
		this.roomnumber = roomnumber;
		this.users = users;
		this.checkin = checkin;
		this.checkout = checkout;
	}


	/////////////////////////////////////////////////////////////////
	/////////////////////// Getters / Setters ///////////////////////
	/////////////////////////////////////////////////////////////////

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

	public HotelUsers getUsers() {
		return users;
	}

	public void setUsers(HotelUsers users) {
		this.users = users;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////// Other Methods ///////////////////////
	/////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return "Reservations [reservationId=" + reservationId + ", roomnumber=" + roomnumber + ", users=" + users
				+ ", checkin=" + checkin + ", checkout=" + checkout + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reservationId;
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
		Reservations other = (Reservations) obj;
		if (reservationId != other.reservationId)
			return false;
		return true;
	}

}
