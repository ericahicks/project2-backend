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
	
	/////////////////////////////////////////////////////////////////
	////////////////////// Instance Variables  //////////////////////
	/////////////////////////////////////////////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservationid")
	private int reservationId;
	
	@ManyToOne
	@JoinColumn(name = "roomnumber")
	@JsonIdentityReference(alwaysAsId = true)
	private Rooms room;
	
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
	public Reservations(int reservationId, Rooms room, HotelUsers users, @NotNull LocalDate checkin,
			@NotNull LocalDate checkout) {
		super();
		this.reservationId = reservationId;
		this.room = room;
		this.users = users;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	// Constructor using all the fields except id
	public Reservations(Rooms room, HotelUsers users, @NotNull LocalDate checkin, @NotNull LocalDate checkout) {
		super();
		this.room = room;
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

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reservationId;
		return result;
	}

	@Override
	public String toString() {
		return "Reservations [reservationId=" + reservationId + ", room=" + room + ", users=" + users + ", checkin="
				+ checkin + ", checkout=" + checkout + "]";
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
